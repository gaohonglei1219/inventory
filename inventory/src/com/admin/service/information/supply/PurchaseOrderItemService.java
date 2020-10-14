package com.admin.service.information.supply;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.Page;
import com.admin.util.PageData;
import com.admin.util.StringUtil;

@Service
public class PurchaseOrderItemService{
	
	@Autowired
	private DaoSupport dao;
	
	private String mapperStr = "PurchaseOrderItemMapper.";
	/**
	 * 插入一个
	 * @param page
	 * @return
	 */
	public boolean insertSup(PageData pd){
		StringUtil.columnTypeChange(pd, "orderId", "int");
		StringUtil.columnTypeChange(pd, "purchaseQuantity", "int");
		StringUtil.columnTypeChange(pd, "purchaseUnitPrice", "float");
		StringUtil.columnTypeChange(pd, "purchaseTotalPrice", "float");
		int number = 0;
		try{
			number = (int) dao.save(mapperStr+"insert",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	
	/**
	 * 根据id修改一个信息
	 * @param page
	 * @return
	 */
	public boolean updateById(PageData pd){
		StringUtil.columnTypeChange(pd, "orderId", "int");
		StringUtil.columnTypeChange(pd, "purchaseQuantity", "int");
		StringUtil.columnTypeChange(pd, "purchaseUnitPrice", "float");
		StringUtil.columnTypeChange(pd, "purchaseTotalPrice", "float");
		StringUtil.columnTypeChange(pd, "purchaseStateId", "int");
		int number = 0;
		try{
			number = (int) dao.update(mapperStr+"updateById",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	
	/**
	 * 根据id删除一个
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
	 * 根据条件查询列表
	 * @param page
	 * @return
	 */
	public List<PageData> queryListPage(Page page){
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
	public PageData queryById(PageData pd){
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
	 * 获取同一个大订单下的数目统计
	 * @param pd
	 * @return
	 */
	public Map<String,String> getCountByOrder(PageData pd){
		Map<String,String> res = null;
		try {
			res = (Map<String,String>) dao.findForObject(mapperStr+"getCountByOrder",pd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public List<PageData> getAllState(){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"getAllState", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
}
