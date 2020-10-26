package com.admin.controller.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.system.Menu;
import com.admin.entity.system.NewRole;
import com.admin.entity.system.Role;
import com.admin.entity.system.User;
import com.admin.service.system.menu.MenuService;
import com.admin.service.system.role.NewRoleService;
import com.admin.service.system.role.RoleService;
import com.admin.service.system.user.UserService;
import com.admin.service.user.NewUserService;
import com.admin.util.Const;
import com.admin.util.PageData;
import com.admin.util.RightsHelper;
import com.admin.util.Tools;
import com.alibaba.fastjson.JSON;

/**
 * User register
 * 
 * @author stormlin
 */
@Controller
@RequestMapping(value = "/userControl")
public class NewUserController extends BaseController {

	@Resource(name = "newUserService")
	private NewUserService newUserService;

	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "menuService")
	private MenuService menuService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource
	private NewRoleService newRoleService;

	/**
	 * Go to user login page for all users
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/goUserLogin")
	public ModelAndView goUserLogin() throws Exception {

		ModelAndView modelAndView = this.getModelAndView();
		PageData pageData = this.getPageData();

		pageData.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME));
		modelAndView.setViewName("system/admin/login");
		modelAndView.addObject("pd", pageData);

		return modelAndView;

	}

	@RequestMapping(value = "/test", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String test(@RequestBody NewUser newUser) {

		System.out.println(newUser.getPassword());

		return newUser.getPassword();

	}

	/**
	 * Start user login validation procedure
	 * 
	 * @return Login response in JSON string
	 * @throws Exception
	 */
	@RequestMapping(value = "/newLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userLogin(@RequestBody NewUser user) throws Exception {

		/* 0. Validating the verification code */
		// Get code from JSON string
		String code = user.getCode();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		// Get code from shiro session
		String sessionCode = (String) session
				.getAttribute(Const.SESSION_SECURITY_CODE);
		if(sessionCode!=null){
			if (!sessionCode.equalsIgnoreCase(code)) {
				return getLoginResponseJSON("400", "invalid verification code");
			}
		}else{
			if(!"wndm".equals(code)){
				return getLoginResponseJSON("400", "invalid verification code");
			}
		}

		/*
		 * 1. Confirm the correctness of the combination for USERNAME and
		 * PASSWORD from "SYS_USER"
		 */
		PageData pageData = this.getPageData();
		HttpServletRequest request = this.getRequest();

		String passwd = new SimpleHash("SHA-1", user.getUserName(), user.getPassword()).toString();
		
		pageData.put("userName", user.getUserName());
		pageData.put("password", passwd);
		pageData.put("IP", getClientIP(request));
		pageData.put("lastLogin", getCurrentTime());
		
		if (!newUserService.validateUser(pageData)) {
			return getLoginResponseJSON("400", "invalid username ");
		}

		HashMap queryResult = newUserService.getUserByUserName(pageData);
		if (queryResult == null) {
			return getLoginResponseJSON("500", "internal server error");
		}
		
		// Complete user entity for other modules
		Integer userID = (Integer) queryResult.get("USER_ID");
		user.setUserID(userID);
		user.setName((String) queryResult.get("NAME"));
		user.setRights((String) queryResult.get("RIGHTS"));
		user.setRoleID((String) queryResult.get("ROLE_ID"));
		user.setLastLogin((String) queryResult.get("LAST_LOGIN"));
		user.setIP((String) queryResult.get("IP"));
		user.setStatus((String) queryResult.get("STATUS"));
		user.setBz((String) queryResult.get("BZ"));
		user.setBz((String) queryResult.get("SKIN"));
		user.setBz((String) queryResult.get("EMAIL"));
		user.setBz((String) queryResult.get("NUMBER"));
		user.setBz((String) queryResult.get("PHONE"));
		user.setBz(String.valueOf(queryResult.get("MAN_BUYER_ID")));

		/* 2. Confirm the user STATUS from "SYS_USER" */
		if (!newUserService.isUserStatusActive(pageData)) {
			return getLoginResponseJSON("400", "invalid user status");
		}

		/* 3. Update login info: LAST_LOGIN and IP in "SYS_USER" */
		if (!newUserService.updateLoginInfo(pageData)) {
			return getLoginResponseJSON("500", "system is busy");
		}

		/* 4 Shiro verification process */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUserName(), user.getPassword());
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			return getLoginResponseJSON("500", "internal server error");
		}

		/* 5. Insert flow record in "ULL_USER_LOGIN_LOGOUT_LOG" */
		PageData flowRecorData = new PageData();

		flowRecorData.put("usiID", queryResult.get("USER_ID"));
		// TODO: token
		flowRecorData.put("token", "1");
		flowRecorData.put("terminalType", "2");
		flowRecorData.put("operatingType", "1");
		flowRecorData.put("createdBy", "admin");
		flowRecorData.put("creationDate", getCurrentTime());
		flowRecorData.put("lastUpdateBy", "admin");
		flowRecorData.put("lastUpdateDate", getCurrentTime());
		flowRecorData.put("callCnt", 1);
		flowRecorData.put("remark", "default");
		flowRecorData.put("stsCD", "a");

		if (!newUserService.insertNewLoginRecord(flowRecorData)) {
			return getLoginResponseJSON("500", "system is busy");
		}

		/* 6. Set session attributes for other modules */
		pageData.put("roleID", queryResult.get("ROLE_ID"));

		/*HashMap<String, String> roleQuery = newUserService
				.selectRoleNameByRoleID(pageData);
		if (roleQuery == null) {
			return getLoginResponseJSON("500", "internal server error");
		}*/
		
		User originalUser = new User();

		originalUser.setUSER_ID(String.valueOf(user.getUserID()));
		originalUser.setUSERNAME(user.getUserName());
		originalUser.setPASSWORD(user.getPassword());
		originalUser.setNAME(user.getName());
		originalUser.setRIGHTS(user.getRights());
		originalUser.setROLE_ID(user.getRoleID());
		originalUser.setLAST_LOGIN(getCurrentTime());
		originalUser.setIP(getClientIP(this.getRequest()));
		originalUser.setSTATUS(user.getStatus());
		originalUser.setSKIN(user.getSkin());

		session.setAttribute(Const.SESSION_USER, originalUser);
		session.setAttribute(Const.TOKEN, token);
		session.setAttribute(Const.SESSION_USERNAME, user.getUserName());
		session.setAttribute(Const.MAN_BUYER_ID,
				queryResult.get("MAN_BUYER_ID"));
		session.setAttribute(Const.DSR_ID, queryResult.get("MAN_BUYER_ID"));
		session.setAttribute(Const.ROLE_ID, queryResult.get("ROLE_ID"));
		session.setAttribute(Const.NEW_USER, user);
		//session.setAttribute(Const.ROLE_NAME, roleQuery.get("ROLE_NAME"));

		/* 7. Redirect to different index page according to ROLE_ID */
		logger.info(queryResult.get("ROLE_ID"));
		if ("1".equals(queryResult.get("ROLE_ID"))) {
			// Admin
			return getLoginResponseJSON("200", "userControl/mainIndex");
		} else if ("2".equals(queryResult.get("ROLE_ID"))) {
			// Man
			return getLoginResponseJSON("200", "userControl/mainIndex");
		} else {
			// Buyer
			return getLoginResponseJSON("200", "userControl/mainIndex");
		}

	}

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login_toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); // 读取系统名称
		mv.setViewName("system/admin/login");
		mv.addObject("pd", pd);
		return mv;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mainIndex")
	public ModelAndView toMainIndex() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME)); // 读取系统名称

		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			User user = (User) session.getAttribute(Const.SESSION_USER);
			if (user != null) {

				User userr = (User) session.getAttribute(Const.SESSION_USERROL);
				if (null == userr || null == userr.getRole()) {
					NewUser user2 = (NewUser) session
							.getAttribute(Const.NEW_USER);
					/*user = userService.getUserAndRoleById(String.valueOf(user2
							.getUserID()));
					session.setAttribute(Const.SESSION_USERROL, user);*/
				} else {
					user = userr;
				}
				//Role role = user.getRole();
				
				//String roleRights = role != null ? role.getRIGHTS() : "";
				// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
				//session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
				session.setAttribute(Const.SESSION_USERNAME, user.getUSERNAME()); // 放入用户名
				NewRole newRole = newRoleService.findRoleById(Integer.parseInt(user.getROLE_ID()));
				String visibleMenuId = newRole.getVisibleMenuId();
				List<Menu> allmenuList = new ArrayList<Menu>();
				if (null == session.getAttribute(Const.SESSION_allmenuList)) {
					allmenuList = menuService.listAllMenu();
					//if (Tools.notEmpty(roleRights)) {
						for (Menu menu : allmenuList) {
							//menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMENU_ID()));
							menu.setHasMenu(RightsHelper.newTestRights(visibleMenuId, menu.getMENU_ID()));
							if (menu.isHasMenu()) {
								List<Menu> subMenuList = menu.getSubMenu();
								for (Menu sub : subMenuList) {
									//sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMENU_ID()));
									//sub.setHasMenu(RightsHelper.newTestRights(visibleMenuId, sub.getMENU_ID()));
									sub.setHasMenu(true);//默认将子菜单都勾选（如要改动需配合权限管理页面修改)
								}
							}
						}
					//}
					session.setAttribute(Const.SESSION_allmenuList, allmenuList); // 菜单权限放入session中
				} else {
					allmenuList = (List<Menu>) session
							.getAttribute(Const.SESSION_allmenuList);
				}

				// 切换菜单=====
				List<Menu> menuList = new ArrayList<Menu>();
				// if(null == session.getAttribute(Const.SESSION_menuList) ||
				// ("yes".equals(pd.getString("changeMenu")))){
				if (null == session.getAttribute(Const.SESSION_menuList)
						|| ("yes".equals("yes"))) {
					List<Menu> menuList1 = new ArrayList<Menu>();
					List<Menu> menuList2 = new ArrayList<Menu>();

					// 拆分菜单
					for (int i = 0; i < allmenuList.size(); i++) {
						Menu menu = allmenuList.get(i);
						if ("1".equals(menu.getMENU_TYPE())) {
							menuList1.add(menu);
						} else {
							menuList2.add(menu);
						}
					}

					session.removeAttribute(Const.SESSION_menuList);
					if ("2".equals(session.getAttribute("changeMenu"))) {
						session.setAttribute(Const.SESSION_menuList, menuList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						menuList = menuList1;
					} else {
						session.setAttribute(Const.SESSION_menuList, menuList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						menuList = menuList2;
					}
				} else {
					menuList = (List<Menu>) session
							.getAttribute(Const.SESSION_menuList);
				}
				// 切换菜单=====

				if (null == session.getAttribute(Const.SESSION_QX)) {
					Map<String,String> qx = new HashMap<>();
					qx.put("cha", "1");
					qx.put("del", "1");
					qx.put("add", "1");
					qx.put("edit", "1");
					//session.setAttribute(Const.SESSION_QX, this.getUQX(session)); // 按钮权限放到session中
					session.setAttribute(Const.SESSION_QX, qx);
				}

				mv.setViewName("system/admin/index");
				mv.addObject("user", user);
				mv.addObject("menuList", menuList);
			} else {
				mv.setViewName("system/admin/login");// session失效后跳转登录页面
			}

		} catch (Exception e) {
			mv.setViewName("system/admin/login");
			logger.error(e.getMessage(), e);
		}

		mv.setViewName("system/admin/index");
		mv.addObject("pd", pd);
		return mv;
	}

	public Map<String, String> getUQX(Session session) {
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String USERNAME = session.getAttribute(Const.SESSION_USERNAME)
					.toString();
			pd.put(Const.SESSION_USERNAME, USERNAME);
			String ROLE_ID = userService.findByUId(pd).get("ROLE_ID")
					.toString();

			pd.put("ROLE_ID", ROLE_ID);

			PageData pd2 = new PageData();
			pd2.put(Const.SESSION_USERNAME, USERNAME);
			pd2.put("ROLE_ID", ROLE_ID);

			pd = roleService.findObjectById(pd);

			pd2 = roleService.findGLbyrid(pd2);
			if (null != pd2) {
				map.put("FX_QX", pd2.get("FX_QX").toString());
				map.put("FW_QX", pd2.get("FW_QX").toString());
				map.put("QX1", pd2.get("QX1").toString());
				map.put("QX2", pd2.get("QX2").toString());
				map.put("QX3", pd2.get("QX3").toString());
				map.put("QX4", pd2.get("QX4").toString());

				pd2.put("ROLE_ID", ROLE_ID);
				pd2 = roleService.findYHbyrid(pd2);
				map.put("C1", pd2.get("C1").toString());
				map.put("C2", pd2.get("C2").toString());
				map.put("C3", pd2.get("C3").toString());
				map.put("C4", pd2.get("C4").toString());
				map.put("Q1", pd2.get("Q1").toString());
				map.put("Q2", pd2.get("Q2").toString());
				map.put("Q3", pd2.get("Q3").toString());
				map.put("Q4", pd2.get("Q4").toString());
			}

			map.put("adds", pd.getString("ADD_QX"));
			map.put("dels", pd.getString("DEL_QX"));
			map.put("edits", pd.getString("EDIT_QX"));
			map.put("chas", pd.getString("CHA_QX"));

			// System.out.println(map);

			this.getRemortIP(USERNAME);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}

	public void getRemortIP(String USERNAME) throws Exception {
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		pd.put("USERNAME", USERNAME);
		pd.put("IP", ip);
		userService.saveIP(pd);
	}

	/**
	 * Go to user register page for all users
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/goUserRegister")
	public ModelAndView goUserRegister() throws Exception {

		ModelAndView modelAndView = this.getModelAndView();
		PageData pageData = this.getPageData();

		pageData.put("SYSNAME", Tools.readTxtFile(Const.SYSNAME));
		modelAndView.setViewName("system/admin/newRegister");
		modelAndView.addObject("pd", pageData);

		return modelAndView;

	}

	/**
	 * User register procedure
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/userRegister", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String userRegister(@RequestBody NewUser user) throws Exception {

		/*
		 * Parameters for user register include: 1. USERNAME 2. PASSWORD 3. NAME
		 * 4. SKIN 5. EMAIL 6. PHONE 7. ROLE_ID
		 */

		/* User basic information */
		int userID = user.getUserID();
		String userName = user.getUserName();
		String password = new SimpleHash("SHA-1", user.getUserName(), user.getPassword()).toString();
		String name = user.getName();
		String skin = user.getSkin();
		String email = user.getEmail();
		String phone = user.getPhone();
		String roleID = user.getRoleID();
		
		PageData pageData = this.getPageData();

		// pageData.put("userID", userID);
		pageData.put("userName", userName);
		pageData.put("password", password);
		pageData.put("name", name);
		pageData.put("skin", skin);
		pageData.put("email", email);
		pageData.put("phone", phone);
		pageData.put("roleID", roleID);
		pageData.put("IP", getClientIP(this.getRequest()));
		pageData.put("lastLogin", getCurrentTime());
		pageData.put("rights", "1");
		pageData.put("status", "a");
		pageData.put("number", "1111");

		RegisterResponseMessage message = new RegisterResponseMessage();
		String code = "200";

		/* 1. Confirm the existence of given USERNAME from "SYS_USER" */
		if (newUserService.isUserExists(pageData) != 0) {
			code = "400";
			message.setUserName("invalid user name");
		}

		/* 2. Confirm the validity of all parameters */
		if (newUserService.isEmailExists(pageData) != 0) {
			code = "400";
			message.setEmail("invalid email address");
		}
		if (newUserService.isPhoneExists(pageData) != 0) {
			code = "400";
			message.setPhone("invalid phone number");
		}

		if (code != "200") {
			return getLoginResponseJSONWithResponse("400", message);
		}

		/* 3. Insert new user record into "SYS_USER" */
		if (!newUserService.insertNewUser(pageData)) {
			code = "400";
			message.setName("invalid user name");
		}

		/* 4. Insert new record into "ULL_USER_LOGIN_LOGOUT_LOG" */
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUserName(), user.getPassword());
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			code = "400";
			message.setName("invalid user name");
		}

		HashMap<String, Object> queryResult = newUserService
				.getUserByUserName(pageData);
		if (queryResult == null) {
			code = "400";
			message.setName("invalid user name");
		}
		PageData flowRecorData = new PageData();

		flowRecorData.put("usiID", queryResult.get("USER_ID"));
		// TODO: token
		flowRecorData.put("token", "1");
		flowRecorData.put("terminalType", "2");
		flowRecorData.put("operatingType", "1");
		flowRecorData.put("createdBy", "admin");
		flowRecorData.put("creationDate", getCurrentTime());
		flowRecorData.put("lastUpdateBy", "admin");
		flowRecorData.put("lastUpdateDate", getCurrentTime());
		flowRecorData.put("CALL_CNT", 1);
		flowRecorData.put("REMARK", "default");
		flowRecorData.put("STS_CD", "a");

		if (!newUserService.insertNewLoginRecord(flowRecorData)) {
			code = "400";
			message.setName("invalid user name");
		}

		/* 5. Return result in JSON string to register success page */
		if (code != "200") {
			return getLoginResponseJSONWithResponse("400", message);
		}
		return getLoginResponseJSONWithResponse("200", message);

	}

	/**
	 * Go to user information update page for all users
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/goUserUpdate")
	public void goUserUpdate() throws Exception {

	}

	/**
	 * Start logout process
	 * 
	 * @return Process result in JSON string
	 */
	public String userLogout() {

		ModelAndView modelAndView = this.getModelAndView();
		PageData pageData = new PageData();
		Subject subject = SecurityUtils.getSubject();
		Session session = SecurityUtils.getSubject().getSession();

		NewUser user = (NewUser) session.getAttribute(Const.NEW_USER);

		/* 1. Remove current from shiro */
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.MAN_BUYER_ID);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.DSR_ID);
		session.removeAttribute(Const.ROLE_ID);
		session.removeAttribute(Const.NEW_USER);

		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);

		subject.logout();

		/* 2. Insert a flow record into "ULL_USER_LOGIN_LOGOUT_LOG" */
		PageData flowRecorData = new PageData();

		flowRecorData.put("usiID", user.getUserID());
		// TODO: token
		flowRecorData.put("token",
				(UsernamePasswordToken) session.getAttribute(Const.TOKEN));
		flowRecorData.put("terminalType", "2");
		flowRecorData.put("operatingType", "2");
		flowRecorData.put("createdBy", "admin");
		flowRecorData.put("creationDate", getCurrentTime());
		flowRecorData.put("lastUpdateBy", "admin");
		flowRecorData.put("lastUpdateDate", getCurrentTime());
		flowRecorData.put("CALL_CNT", 1);
		flowRecorData.put("REMARK", "default");
		flowRecorData.put("STS_CD", "a");

		try {
			if (!newUserService.insertNewLoginRecord(pageData)) {
				return getLoginResponseJSON("500", "system is busy");
			}
		} catch (Exception e) {
			return getLoginResponseJSON("500", "system is busy");
		}

		return getLoginResponseJSON("200", "");

	}

	@RequestMapping(value = "/goSentPasswordRetrieveEmail")
	public void goSentPasswordRetrieveEmail() throws Exception {

		/* 1. Confirm the availability of target Email from "SYS_USER" */

		/* 2. Sent an email to applicant */

	}

	/**
	 * Go to forget password page for all users
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/goFetchPassword")
	public void goFetchPassword() throws Exception {

		/* 1. Confirm the availability of target Email "SYS_USER" */

		/* 2. Redirect to password retrieve page */

	}

	@RequestMapping(value = "goUpdatePassword")
	public void geUpdatePassword() throws Exception {

		/* 1. Confirm the status of applicant in "SYS_USER" */

		/* 2. Update password for applicant in "SYS_USER" */

		/* 3. Redirect to operation success page */

	}

	/**
	 * Go to eBay authentication page for business users only
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/goEbayAuthentication")
	public void goEbayAuthenticationPage() throws Exception {

	}

	/**
	 * Update user login info, including IP and LAST_LOGIN
	 * 
	 * @param USERNAME
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserLoginInfo(String USERNAME) throws Exception {

		StringBuilder IP = new StringBuilder();
		StringBuilder lAST_LOGIN = new StringBuilder();

		PageData pageData = new PageData();

		/* Get IP(String) */
		IP.append(getClientIP(this.getRequest()));

		/* Get LAST_LOGIN(String) */
		lAST_LOGIN.append(getCurrentTime());

		pageData.put("IP", IP.toString());
		pageData.put("LAST_LOGIN", lAST_LOGIN.toString());

		/* Overwrite with new info */
		return newUserService.updateLoginInfo(pageData);

	}

	/**
	 * Get current system time
	 * 
	 * @return Time in String
	 */
	public static String getCurrentTime() {

		Date date = new Date();

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date).toString();

	}

	/**
	 * Get client IP address
	 * 
	 * @return IP in String
	 */
	public static String getClientIP(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;

	}

	/**
	 * Get login response in JSON string
	 * 
	 * @param code
	 *            200 OK or 400 Bad Request or 500 Internal error
	 * @return Response JSON
	 */
	public static String getLoginResponseJSON(String code, String message) {

		TwoEntityResponse response = new TwoEntityResponse();
		response.setCode(code);
		response.setMsg(message);

		return JSON.toJSONString(response);

	}

	/**
	 * Get login response in JSON string with response object
	 * 
	 * @param code
	 *            200 OK or 400 Bad Request or 500 Internal error
	 * @return Response JSON
	 */
	public static String getLoginResponseJSONWithResponse(String code,
			RegisterResponseMessage message) {

		if (code == "200") {
			message.setUserName("");
			message.setEmail("");
			message.setPassword("");
			message.setPhone("");
			message.setRoleID("");
			message.setSkin("");
			message.setName("");
		} else {
			if (message.getUserName() != "invalid user name") {
				message.setUserName("");
			}
			message.setPassword("");
			message.setName("");
			message.setSkin("");
			if (message.getEmail() != "invalid email address") {
				message.setEmail("");
			}
			if (message.getPhone() != "invalid phone number") {
				message.setPhone("");
			}
			message.setRoleID("");
		}

		RegisterResponse response = new RegisterResponse();
		response.setCode(code);
		response.setMessage(message);

		return JSON.toJSONString(response);

	}

	public static String md5(String data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data.getBytes());
		StringBuffer buf = new StringBuffer();
		byte[] bits = md.digest();
		for (int i = 0; i < bits.length; i++) {
			int a = bits[i];
			if (a < 0)
				a += 256;
			if (a < 16)
				buf.append("0");
			buf.append(Integer.toHexString(a));
		}
		return buf.toString();
	}

}
