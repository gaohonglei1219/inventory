package com.admin.service.information.supply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.Page;
import com.admin.util.PageData;

@Service
public class SingleSupService {
	
	@Autowired
	private DaoSupport dao;
	
	private String mapperStr = "SingleSupMapper";
	/**
	 * 插入
	 * @param page
	 * @return
	 */
	public boolean insert(PageData pd){
		int number = 0;
		try{
			number = (int) dao.save(mapperStr+".insert",pd);
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
			number = (int) dao.delete(mapperStr+".delById",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据id修改一个供应商信息
	 * @param page
	 * @return
	 */
	public boolean updateById(PageData page){
		int number = 0;
		try{
			number = (int) dao.update(mapperStr+".updateById",page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据条件查询供应商列表
	 * @param page
	 * @return
	 */
	public List<PageData> querylistPage(Page page){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+".querylistPage", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据id查询供应商信息
	 * @param pd
	 * @return
	 */
	public PageData queryById(PageData pd){
		PageData res = null;
		try {
			res = (PageData) dao.findForObject(mapperStr+".queryById",pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}
