package com.admin.service.information.supply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.Page;
import com.admin.util.PageData;

@Service
public class SingleStateService {
	
	@Autowired
	private DaoSupport dao;
	
	private String mapperStr = "SingleStateMapper.";
	/**
	 * 插入一个
	 * @param page
	 * @return
	 */
	public boolean insertSup(PageData pd){
		int number = 0;
		try{
			number = (int) dao.save(mapperStr+"insert",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据id删除
	 * @param page
	 * @return
	 */
	public boolean delById(PageData page){
		int number = 0;
		try{
			number = (int) dao.delete(mapperStr+"delById",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据id修改一个
	 * @param page
	 * @return
	 */
	public boolean updateById(PageData pd){
		pd.put("stateId", Integer.parseInt(pd.getString("stateId")));
		int number = 0;
		try{
			number = (int) dao.update(mapperStr+"updateById",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据条件查询列表
	 * @param page
	 * @return
	 */
	public List<PageData> querySup(Page page){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"querylistPage", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据id查询信息
	 * @param pd
	 * @return
	 */
	public PageData queryData(PageData pd){
		PageData res = null;
		try {
			res = (PageData) dao.findForObject(mapperStr+"queryById",pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	/**
	 * 查询所有id和name
	 * @return
	 */
	public List<Map<String,String>> getAllIdName(){
		List<Map<String,String>> list = null;
		try {
			list = (List<Map<String, String>>) dao.findForList(mapperStr+"getAllIdName",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
