package com.admin.service.system.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.system.NewRole;

@Service("NewRoleService")
public class NewRoleService {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	//新增角色
	public void addRole(NewRole role) throws Exception{
		if(role.getRoleId()!=0){
			dao.save("NewRoleMapper.addRole", role);
		}
	}
	//根据ID删除角色
	public void deleteRoleById(NewRole role) throws Exception{
		if(role.getRoleId()!=0){
			dao.save("NewRoleMapper.deleteRoleById",role);
		}
	}
	//根据ID修改角色
	public void editRoleById(NewRole role) throws Exception{
		if(role.getRoleId()!=0){
			dao.save("NewRoleMapper.editRoleById", role);
		}
	}
	//根据ID查看角色
	public NewRole findRoleById(int role_id) throws Exception{
		return (NewRole)dao.findForObject("NewRoleMapper.findRoleById", role_id);
	}
	//找到当前最大的角色ID
	public int findMaxRoleId() throws Exception{
		return (int)dao.findForObject("NewRoleMapper.findMaxRoleId", null);
	}
	//查看所有角色
	public List<NewRole> findAllRole() throws Exception{
		return (List<NewRole>)dao.findForList("NewRoleMapper.findAllRole", null);
	}
	//根据菜单ID和角色ID设置该菜单对该角色是否可见。
	public void setVisibleByMenuIdAndRoleId(NewRole role) throws Exception{	
		dao.save("NewRoleMapper.setVisibleByMenuIdAndRoleId", role);
	}
}
