package com.admin.controller.system.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.controller.base.BaseController;
import com.admin.entity.system.NewMenu;
import com.admin.entity.system.NewRole;
import com.admin.service.system.menu.NewMenuService;
import com.admin.service.system.role.NewRoleService;
import com.admin.util.Const;

/**
 * @Date 2017年7月20日上午
 * @author 张通
 * @Desc 提供前端模拟调用的控制层
 */

@Controller
@RequestMapping(value = "/newMenu")
public class NewMenuController extends BaseController {

	@Autowired
	private NewMenuService newMenuService;
	@Autowired
	private NewRoleService newRoleService;

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 显示所有顶级菜单列表
	 */
	@RequestMapping(value = "/listTopMenu")
	public ModelAndView listTopMenu() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<NewMenu> newMenuList = newMenuService.listMenuByParentId(0);
			modelAndView.addObject("menuList", newMenuList);
			modelAndView.setViewName("system/NewMenu/menuList");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return modelAndView;
	}

	/**
	 * @Date 2017年7月22日上午
	 * @author 张通
	 * @Desc 显示当前角色可见的顶级菜单列表
	 */
	@RequestMapping(value = "/listVisibleTopMenu")
	public ModelAndView listVisibleTopMenu() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			int roleId = Integer.parseInt((String) session
					.getAttribute(Const.ROLE_ID));// 这里联调的时候要取ROLE_ID;
			List<NewMenu> visibleMenuList = new ArrayList<NewMenu>();
			if (1 == roleId) {//管理员用户，可以查看所有菜单
				visibleMenuList = (List<NewMenu>)newMenuService.listMenuByParentId(0);
			} else {
				NewRole newRole = newRoleService.findRoleById(roleId);
				String rights = newRole.getVisibleMenuId();

				// 把取到的对当前角色可用的菜单id切分成一个个单独的id
				ArrayList<String> right = new ArrayList<String>();
				
				StringTokenizer toKenizer = new StringTokenizer(rights, ",");
				while (toKenizer.hasMoreElements()) {
					right.add(toKenizer.nextToken());
				}
				Iterator<String> iterator = right.iterator();
				while (iterator.hasNext()) {
					NewMenu newMenu = newMenuService.findMenuByMenuId(Integer
							.valueOf(iterator.next()));
					if (null != newMenu) {
						visibleMenuList.add(newMenu);
					}
				}
			}
			modelAndView.addObject("menuList", visibleMenuList);
			modelAndView.setViewName("system/NewMenu/menuList");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 请求新增菜单页面
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<NewMenu> newMenuList = newMenuService.listMenuByParentId(0);
		modelAndView.addObject("menuList", newMenuList);
		modelAndView.setViewName("system/NewMenu/menuAdd");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 保存新增菜单信息
	 */
	@RequestMapping(value = "/saveAdd", produces = "text/html;charset=UTF-8")
	public ModelAndView saveAdd(NewMenu newMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Integer newId = newMenuService.findMaxMenuId() + 1;
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			newMenu.setMenuId(newId);
			newMenu.setCreatedBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is add");
			newMenu.setStsCd("A");
			newMenuService.addMenu(newMenu);
			modelAndView.addObject("resultMsg", "新增成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "新增失败");
			logger.error("保存信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 请求编辑菜单页面
	 */
	@RequestMapping(value = "/toEdit", produces = "text/html;charset=UTF-8")
	public ModelAndView toEdit(NewMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		NewMenu newMenu = new NewMenu();
		int menuId = menu.getMenuId();
		newMenu = newMenuService.findMenuByMenuId(menuId);
		NewMenu newParentMenu = new NewMenu();
		newParentMenu = newMenuService.findMenuByMenuId(newMenu.getParentId());
		List<NewMenu> newMenuList = newMenuService.listMenuByParentId(0);
		modelAndView.addObject("menuList", newMenuList);
		modelAndView.addObject("parentMenuName", newParentMenu.getMenuName());
		modelAndView.addObject("parentMenuID", newParentMenu.getMenuId());
		modelAndView.addObject("menuId", menu.getMenuId());
		modelAndView.addObject("menuName", newMenu.getMenuName());
		modelAndView.addObject("menuURL", newMenu.getMenuUrl());
		modelAndView.addObject("menuOrder", newMenu.getMenuOrder());
		modelAndView.addObject("menuType", newMenu.getMenuType());
		modelAndView.setViewName("system/NewMenu/menuEdit");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 保存编辑菜单信息
	 */
	@RequestMapping(value = "/saveEdit")
	public ModelAndView saveEdit(NewMenu newMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit");
			newMenuService.editMenuById(newMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月24日上午
	 * @author 张通
	 * @Desc 保存编辑母菜单name
	 */
	@RequestMapping(value = "/saveEditParentMenuName")
	public ModelAndView saveEditParentMenuName(NewMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			NewMenu newMenu = newMenuService.findMenuByMenuId(menu.getMenuId());
			newMenu.setMenuName(menu.getMenuName());
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit name");
			newMenuService.editMenuById(newMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		// modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月24日上午
	 * @author 张通
	 * @Desc 保存编辑母菜单order
	 */
	@RequestMapping(value = "/saveEditParentMenuOrder")
	public ModelAndView saveEditParentMenuOrder(NewMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			NewMenu newMenu = newMenuService.findMenuByMenuId(menu.getMenuId());
			newMenu.setMenuOrder(menu.getMenuOrder());
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit order");
			newMenuService.editMenuById(newMenu);
			modelAndView.addObject("resultMsg", "编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "编辑失败");
			logger.error("保存信息失败", e);
		}
		// modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 保存删除菜单信息
	 */
	@RequestMapping(value = "/saveDelete")
	public ModelAndView saveDelete(NewMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		int menuId = menu.getMenuId();
		try {
			NewMenu newMenu = newMenuService.findMenuByMenuId(menuId);
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is delete");
			newMenuService.deleteMenuById(newMenu);
			modelAndView.addObject("resultMsg", "删除成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "删除失败");
			logger.error("删除信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 请求编辑菜单图标页面
	 */
	@RequestMapping(value = "/toEditIcon")
	public ModelAndView toEditIcon(NewMenu menu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int menuId = menu.getMenuId();
		NewMenu newMenu = newMenuService.findMenuByMenuId(menuId);
		String icon = newMenu.getMenuIcon();
		modelAndView.addObject("menuIcon", icon);
		modelAndView.setViewName("system/NewMenu/menuIcon");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月20日上午
	 * @author 张通
	 * @Desc 保存修改图标信息
	 */
	@RequestMapping(value = "/saveEditIcon")
	public ModelAndView saveEditIcon(NewMenu newMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			newMenu.setLastUpdateBy((String) session
					.getAttribute(Const.SESSION_USERNAME));
			newMenu.setRemark("last action is edit menu icon");
			newMenuService.editMenuIconById(newMenu);
			modelAndView.addObject("resultMsg", "修改图标成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			modelAndView.addObject("resultMsg", "修改图标失败");
			logger.error("修改图标失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * @Date 2017年7月21日上午
	 * @author 张通
	 * @Desc 获取当前菜单的所有子菜单信息
	 */
	@RequestMapping(value = "/getSub")
	public ModelAndView getSub(NewMenu newMenu) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int parentId = newMenu.getMenuId();
		try {
			List<NewMenu> subMenuList = newMenuService
					.listMenuByParentId(parentId);
			modelAndView.addObject("subMenu", subMenuList);
		} catch (Exception e) {
			logger.error("获取子菜单失败", e);
		}

		return modelAndView;
	}
}
