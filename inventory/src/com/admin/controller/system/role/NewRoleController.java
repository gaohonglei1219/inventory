package com.admin.controller.system.role;

import java.util.List;

import com.admin.entity.system.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.admin.controller.base.BaseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.admin.service.system.menu.NewMenuService;
import com.admin.service.system.role.NewRoleService;
import com.admin.util.Const;
@Controller
@RequestMapping(value="/newRole")
public class NewRoleController extends BaseController {
	
	@Autowired
	private NewMenuService newMenuService;
	@Autowired
	private NewRoleService newRoleService;
	
	/**
     显示所有角色列表
	 */
	@RequestMapping(value="/listRole")
	public ModelAndView listRole() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		try{
			List<NewRole> newRoleList =newRoleService.findAllRole();
			modelAndView.addObject("newRoleList", newRoleList);
			modelAndView.setViewName("system/NewRole/RoleList");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return modelAndView;
	}
	
	/** 
	 * 请求新增角色页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("system/NewRole/RoleAdd");
		return modelAndView;
	}
	/**
	 * 保存新增角色信息
	 */
	@RequestMapping(value="/saveAdd", produces="text/html;charset=UTF-8")
	public ModelAndView saveAdd(NewRole role) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		NewRole newRole=new NewRole();
		String roleName=role.getRoleName();
		try{
			newRole.setRoleId(newRoleService.findMaxRoleId()+1);
			newRole.setRoleName(roleName);
			newRole.setCreatedBy((String)session.getAttribute(Const.SESSION_USERNAME));
			newRole.setLastUpdateBy((String)session.getAttribute(Const.SESSION_USERNAME));
			newRole.setStsCd("A");
			newRole.setRemark("123");
			newRoleService.addRole(newRole);
			
			modelAndView.addObject("resultMsg", "新增成功");
			this.getRequest().setCharacterEncoding("UTF-8");			
			}
		catch(Exception e){
			modelAndView.addObject("resultMsg", "新增失败");
			logger.error("保存信息失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}
	/**
	 * 请求编辑
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit( NewRole role )throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		NewRole newRole=new NewRole();
		int roleId=role.getRoleId();
		newRole = newRoleService.findRoleById(roleId);
		modelAndView.addObject("roleName", newRole.getRoleName());
		modelAndView.setViewName("system/NewRole/RoleEdit");
		return modelAndView;
	}
	/**
	 * 保存编辑信息
	 */
	@RequestMapping(value="/saveEdit", produces="text/html;charset=UTF-8")
	public ModelAndView saveEdit(NewRole newRole)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		try{
			newRole.setRemark("上次操作为修改");
			newRole.setLastUpdateBy((String)session.getAttribute(Const.SESSION_USERNAME));
			newRoleService.editRoleById(newRole);   //根据newRole的ID来编辑相同ID的记录变成newRole的数据
			modelAndView.addObject("resultMsg","编辑成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch(Exception e){
			modelAndView.addObject("resultMsg","编辑失败");
			logger.error("编辑失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}

	/**
	 * 保存删除信息
	 */
	@RequestMapping(value="/saveDelete", produces="text/html;charset=UTF-8")
	public ModelAndView saveDelete(NewRole role)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		int roleId=role.getRoleId();
		NewRole newRole=newRoleService.findRoleById(roleId);
		try{
			newRole.setLastUpdateBy((String)session.getAttribute(Const.SESSION_USERNAME));
			newRole.setRemark("删除成功");
			newRoleService.deleteRoleById(newRole);
			modelAndView.addObject("resultMsg", "删除成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch(Exception e){
			modelAndView.addObject("resultMsg", "删除失败");
			logger.error("删除失败", e);
		}
		modelAndView.setViewName("save_result");
        return modelAndView;
	}
	
	/**
	 * 请求角色菜单授权页面（仅顶层菜单）
	 */
	@RequestMapping(value="/toRights")
	public ModelAndView toRights(NewRole role)throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		List<NewMenu>topMenuList = newMenuService.listMenuByParentId(0);  //顶层菜单列
		int roleId=role.getRoleId();
		NewRole newRole = newRoleService.findRoleById(roleId);
		modelAndView.addObject("roleId",role.getRoleId());
		modelAndView.addObject("topMenu",topMenuList);
		modelAndView.addObject("visibleMenu",newRole.getVisibleMenuId()); 
		modelAndView.setViewName("system/NewRole/RoleRights");
		return modelAndView;
	}
	/**
	 * 保存角色菜单权限
	 */
	@RequestMapping(value="/saveRights", produces="text/html;charset=UTF-8")
	public ModelAndView saveRights(RoleIdAndRights roleIdAndRights)throws Exception{  
		ModelAndView modelAndView = new ModelAndView();
		NewRole newRole=newRoleService.findRoleById(roleIdAndRights.getRoleId());
		Subject currentUser=SecurityUtils.getSubject();
	    Session session=currentUser.getSession();
		try{
			String newRights="";
			String[] rights=roleIdAndRights.getRights();
			for(int i=0;i<rights.length-1;i++){
				newRights=newRights.concat(rights[i]);
				newRights=newRights.concat(",");
			}
			newRights=newRights.concat(rights[rights.length-1]);
			newRole.setVisibleMenuId(newRights);
			newRole.setLastUpdateBy((String)session.getAttribute(Const.SESSION_USERNAME));
			newRole.setRemark("last action is update visible menu");
			newRoleService.setVisibleByMenuIdAndRoleId(newRole);
			modelAndView.addObject("resultMsg", "权限更新成功");
			this.getRequest().setCharacterEncoding("UTF-8");
		} catch(Exception e){
			modelAndView.addObject("resultMsg", "权限更新失败");	
			logger.error("权限保存失败", e);
		}
		modelAndView.setViewName("save_result");
		return modelAndView;
	}
}

