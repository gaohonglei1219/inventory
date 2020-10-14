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
public class PurchaseOrderService {
	
	@Autowired
	private DaoSupport dao;
	
	private String mapperStr = "PurchaseOrderMapper.";
	/**
	 * 插入一个
	 * @param page
	 * @return
	 */
	public boolean insertSup(PageData pd){
		StringUtil.columnTypeChange(pd, "orderId", "int");
		StringUtil.columnTypeChange(pd, "orderFrontwarehouseId", "int");
		int number = 0;
		try{
			number = (int) dao.save(mapperStr+"insert",pd);
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
	 * 根据id修改一个信息
	 * @param page
	 * @return
	 */
	public boolean updateById(PageData pd){
		StringUtil.columnTypeChange(pd, "orderId", "int");
		StringUtil.columnTypeChange(pd, "orderFrontwarehouseId", "int");
		StringUtil.columnTypeChange(pd, "orderRunStateId", "int");
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
	 * 根据id查询供应商信息
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
	 * 获取所有的前置仓
	 * @return
	 */
	public List<PageData> getAllFw(){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"getAllFw", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 更新相关数量和总价
	 * @param pd
	 * @return
	 */
	public boolean updateCount(PageData pd){
		int number = 0;
		try{
			number = (int) dao.update(mapperStr+"updateCount",pd);
		}catch(Exception e){
			e.printStackTrace();
		}
		return number>0?true:false;
	}
	
	/**
	 * 获取所有的支付类型
	 * @return
	 */
	public List<PageData> getPayType(){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"getPayType", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有的流转状态
	 * @return
	 */
	public List<PageData> getRunState(){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"getAllRunState", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有的支付状态
	 * @return
	 */
	public List<PageData> getPayState(){
		List<PageData> list = null;
		try {
			list = (List<PageData>) dao.findForList(mapperStr+"getAllPayState", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
