package com.admin.service.information.supply;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.Page;
import com.admin.util.PageData;

@Service
public class SupplierService {
	
	@Autowired
	private DaoSupport dao;
	
	private String mapperStr = "SupplierMapper";
	/**
	 * 插入一个供应商
	 * @param page
	 * @return
	 */
	public boolean insertSup(PageData pd){
		pd.put("typeId", Integer.parseInt(pd.getString("typeId")));
		int number = 0;
		try{
			number = (int) dao.save(mapperStr+".insertSup",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	/**
	 * 根据id删除一个供应商
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
	public boolean updateSup(PageData page){
		int number = 0;
		try{
			number = (int) dao.update(mapperStr+".updateSup",page);
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
	public List<PageData> querySup(Page page){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+".querySuplistPage", page);
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
	public PageData queryData(PageData pd){
		PageData res = null;
		try {
			res = (PageData) dao.findForObject(mapperStr+".querySupById",pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
