package com.admin.service.system.menu;

import java.util.List;

import javax.annotation.Resource;

import com.admin.dao.DaoSupport;

import org.springframework.stereotype.Service;

import com.admin.entity.system.NewMenu;

@Service("NewMenuService")
public class NewMenuService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//新增菜单
	public void addMenu(NewMenu newMenu) throws Exception{
		if(newMenu.getMenuId()!=0){
			dao.save("NewMenuMapper.addMenu", newMenu);
		}
	}
	//根据ID删除菜单
	public void deleteMenuById(NewMenu newMenu) throws Exception{
		if(newMenu.getMenuId()!=0){
			dao.save("NewMenuMapper.deleteMenuById", newMenu);
		}
	}
	//根据菜单ID列出它的子菜单
	public List<NewMenu> listMenuByParentId(int parentId) throws Exception{
		return (List<NewMenu>)dao.findForList("NewMenuMapper.listMenuByParentId",parentId);
	}
	//根据菜单ID修改菜单信息
	public void editMenuById(NewMenu menu) throws Exception{
		dao.save("NewMenuMapper.editMenuById", menu);
	}
	//根据顶级菜单ID修改菜单图片
	public void editMenuIconById(NewMenu menu) throws Exception{
		dao.save("NewMenuMapper.editMenuIconById", menu);
	}
	//找到当前最大的MENU的ID
	public int findMaxMenuId() throws Exception{
		return (int)dao.findForObject("NewMenuMapper.findMaxMenuId", null);
	}
	//根据菜单ID找到该菜单
	public NewMenu findMenuByMenuId(int menu_id) throws Exception{
		return (NewMenu)dao.findForObject("NewMenuMapper.findMenuByMenuId",menu_id);
	}
}

