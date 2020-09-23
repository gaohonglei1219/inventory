package com.admin.service.monitor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.DaoSupport;
import com.admin.entity.MonitorBean;
import com.admin.mapper.MonitorMapper;
import com.admin.util.Logger;
import com.admin.util.PageData;

@Service("monitorService")
public class MonitorService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Resource(name = "daoSupport")
	private DaoSupport daoSupport;
	
	@Autowired
	private MonitorMapper monitorMapper;

	/**
	 * @Date 2017年7月11日下午4:14:31
	 * @author linkaitao
	 * @Desc 模拟结果列表
	 */
	/*public List<PageData> findMonitorListWithPage(PageData pageData)
			throws Exception {
		return (List<PageData>) daoSupport.findForList(
				"MonitorMapper.findMonitorListWithPage", pageData);
	}*/
	public List<MonitorBean> findMonitorList()
			throws Exception {
		return monitorMapper.findMonitorList();
	}

	/**
	 * @Date 2017年7月11日下午4:16:36
	 * @author linkaitao
	 * @Desc 新增模拟结果
	 */
	public void saveMonitor(PageData pageData) throws Exception {
		daoSupport.save("MonitorMapper.saveMonitor", pageData);
	}

	/**
	 * @Date 2017年7月11日下午4:17:52
	 * @author linkaitao
	 * @Desc 更新模拟结果
	 */
	/*public void updateMonitor(PageData pageData) throws Exception {
		daoSupport.update("MonitorMapper.updateMonitor", pageData);
	}*/
	public void updateMonitor(MonitorBean monitorBean) throws Exception {
		monitorMapper.updateMonitor(monitorBean);
	}

	/**
	 * @Date 2017年7月11日下午4:19:10
	 * @author linkaitao
	 * @Desc 根据id查询模拟结果
	 */
	/*public PageData findMonitorById(PageData pageData) throws Exception {
		return (PageData) daoSupport.findForObject(
				"MonitorMapper.findMonitorById", pageData);
	}*/
	public MonitorBean findMonitorById(int morId) throws Exception {
		return monitorMapper.findMonitorById(morId);
	}
	

	/**
	 * @Date 2017年7月17日下午4:19:10
	 * @author linkaitao
	 * @Desc 根据id查询模拟结果
	 */
	public MonitorBean findMonitorByIdWidthReturnObject(PageData pageData)
			throws Exception {
		return (MonitorBean) daoSupport.findForObject(
				"MonitorMapper.findMonitorByIdWidthReturnObject", pageData);
	}

	/**
	 * @Date 2017年7月17日下午4:19:10
	 * @author linkaitao
	 * @Desc 根据id查询模拟结果
	 */
	public MonitorBean findMonitor(MonitorBean monitorBean) throws Exception {
		return (MonitorBean) daoSupport.findForObject(
				"MonitorMapper.findMonitor", monitorBean);
	}

	/**
	 * @Date 2017年7月18日下午9:09:24
	 * @author linkaitao
	 * @Desc
	 */
	public void saveMonitorBean(MonitorBean monitorBean) throws Exception {
		//daoSupport.save("MonitorMapper.saveMonitorBean", monitorBean);
		monitorMapper.saveMonitorBean(monitorBean);
	}

	/**
	 * @Date 2017年7月18日下午9:09:24
	 * @author linkaitao
	 * @Desc 模拟拆单
	 */
	public void updateMonitorBreakOrder() throws Exception {
		/**
		 * 1.随机获取一个sku 2.生成sto,sol,sha 3.拆单生成sao,sal
		 */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		List<PageData> strList = (List<PageData>) daoSupport.findForList(
				"MonitorMapper.findStrList", null);
		logger.debug("strList=" + strList);
		if (strList != null && strList.size() > 0) {
			for (PageData strPageData : strList) {
				String strId = strPageData.get("STR_ID").toString();// 网店id
				String currentDate = simpleDateFormat.format(new Date());

				// 随机获取一个产品
				PageData proData = new PageData();
				proData = (PageData) daoSupport.findForObject(
						"MonitorMapper.findProductRand", null);
				logger.debug("proData=" + proData.toString());

				PageData proPriceData = new PageData();
				proPriceData = (PageData) daoSupport.findForObject(
						"MonitorMapper.findProductPrice", proData);
				String proPrice = proPriceData.get("PRICE").toString();// 商品单价
				logger.debug("proPrice=" + proPrice);
				Calendar calendar = Calendar.getInstance();

				String orderNo = "AO" + calendar.get(Calendar.YEAR)
						+ numberToString(calendar.get(Calendar.MONTH) + 1)
						+ numberToString(calendar.get(Calendar.DAY_OF_MONTH))
						+ numberToString(calendar.get(Calendar.HOUR_OF_DAY))
						+ numberToString(calendar.get(Calendar.MINUTE))
						+ numberToString(calendar.get(Calendar.SECOND))
						+ numberToStringNew(calendar.get(Calendar.MILLISECOND));
				logger.debug("orderNo=" + orderNo);

				PageData stoData = new PageData();
				stoData.put("STR_ID", strId);// 网店id
				stoData.put("ORDER_ID", "AMAZON :  058-1233752-8214740");
				stoData.put("ORDER_CREATED_TIME", currentDate);
				stoData.put("PAID_TIME", currentDate);
				stoData.put("LAST_MODIFIED_TIME", currentDate);
				stoData.put("CREATED_BY", "system");
				stoData.put("CREATION_DATE", currentDate);
				stoData.put("LAST_UPDATE_BY", "system");
				stoData.put("LAST_UPDATE_DATE", currentDate);
				stoData.put("CALL_CNT", "1");
				stoData.put("REMARK", "");
				stoData.put("STS_CD", "A");
				stoData.put("PLATEFORM_TYPE", "1");
				stoData.put("CANCEL_COMPLETE_DATE", currentDate);
				daoSupport.save("MonitorMapper.saveSto", stoData);
				String stoId = stoData.get("STO_ID").toString();
				logger.debug("STO_ID=" + stoId);

				PageData solData = new PageData();
				solData.put("ORDER_ITEM_ID", "1");
				solData.put("STO_ID", stoId);
				solData.put("SALES_PRICE", proPrice);
				solData.put("QTY", "1");
				solData.put("SKU_NO", proData.get("SKU_CD"));
				solData.put("CREATED_BY", "system");
				solData.put("CREATION_DATE", currentDate);
				solData.put("LAST_UPDATE_BY", "system");
				solData.put("LAST_UPDATE_DATE", currentDate);
				solData.put("CALL_CNT", "1");
				solData.put("REMARK", "1");
				solData.put("STS_CD", "A");
				solData.put("BUYER_CHECKOUT_MESSAGE", "");
				daoSupport.save("MonitorMapper.saveSol", solData);
				String solId = solData.get("SOL_ID").toString();
				logger.debug("SOL_ID=" + solId);
				
				//if(1==1) throw new Exception("test transaction callback----------------------");

				PageData shaData = new PageData();
				shaData.put("CREATED_BY", "system");
				shaData.put("CREATION_DATE", currentDate);
				shaData.put("LAST_UPDATE_BY", "system");
				shaData.put("LAST_UPDATE_DATE", currentDate);
				shaData.put("CALL_CNT", "1");
				shaData.put("REMARK", "");
				shaData.put("STS_CD", "A");
				shaData.put("FAMILY_NAME", "");
				shaData.put("GIVEN_NAME", "Stephanie Valliere");
				shaData.put("COUNTRY_NAME", "United States");
				shaData.put("COUNTRY_ISO_CD", "US");
				shaData.put("STATE_OR_PROVINCE_NAME", "Massachusetts");
				shaData.put("STATE_OR_PROVINCE_CD", "MA");
				shaData.put("CITY_NAME", "West Wareham");
				shaData.put("ADDRESS_LINE1", "14 Griffin Way");
				shaData.put("ADDRESS_LINE2", "");
				shaData.put("ADDRESS_LINE3", "");
				shaData.put("POSTAL_CD", "02576-1361");
				shaData.put("CONTACT_PHONE_NO", "774 204 2210");
				shaData.put("EMAIL", "henryc524@yahoo.com");
				shaData.put("STO_ID", stoId);
				shaData.put("CARRIER_NAME", "");
				daoSupport.save("MonitorMapper.saveSha", shaData);
				logger.debug("sha=" + shaData);

				// 拆分插入sao
				PageData saoData = new PageData();
				saoData.put("CREATED_BY", "system");
				saoData.put("CREATION_DATE", currentDate);
				saoData.put("LAST_UPDATE_BY", "system");
				saoData.put("LAST_UPDATE_DATE", currentDate);
				saoData.put("CALL_CNT", "1");
				saoData.put("REMARK", "");
				saoData.put("STS_CD", "A");
				saoData.put("MAN_ID", proData.get("MAN_ID"));
				saoData.put("WAR_ID", "1");
				saoData.put("SYNC_DATE", currentDate);
				saoData.put("PAYMENT_DATE", currentDate);
				saoData.put("PUSH_DATE", null);
				saoData.put("TRACK_NO_DATE", null);
				saoData.put("FULFILLMENT_DATE", null);
				saoData.put("SETTLEMENT_DATE", null);
				saoData.put("PRODUCT_AMOUNT", proPrice);
				saoData.put("FREIGHT_COST", "0");
				saoData.put("HANDLING_FEE", "0");
				saoData.put("CUSTOMER_REMARK", "");
				saoData.put("ORDER_NO", orderNo);
				saoData.put("ORDER_STS", "1");
				saoData.put("REFUND_STS", null);
				saoData.put("DELIVERY_STS", null);
				saoData.put("STO_ID", stoId);
				saoData.put("LAST_FREIGHT_COST", null);
				saoData.put("CANCLE_TIME", null);
				saoData.put("AUTO_PAY_STATUS", "1");
				saoData.put("BUYER_CHECKOUT_MESSAGE", "");
				saoData.put("AUTO_PAY_TIME", null);
				daoSupport.save("MonitorMapper.saveSao", saoData);
				String saoId = saoData.get("SAO_ID").toString();
				logger.debug("SAO_ID=" + saoId);

				// 拆分插入sal
				PageData salData = new PageData();
				salData.put("CREATED_BY", "system");
				salData.put("CREATION_DATE", currentDate);
				salData.put("LAST_UPDATE_BY", "system");
				salData.put("LAST_UPDATE_DATE", currentDate);
				salData.put("CALL_CNT", "1");
				salData.put("REMARK", "");
				salData.put("STS_CD", "A");
				salData.put("SAO_ID", saoId);
				salData.put("PRO_ID", proData.get("PRO_ID"));
				salData.put("QTY", "1");
				salData.put("PRICE", proPrice);
				salData.put("SOL_ID", solId);
				salData.put("TRACKING_NO", "");
				salData.put("WSP_NAME", "");
				daoSupport.save("MonitorMapper.saveSal", salData);
				String salId = salData.get("SAl_ID").toString();
				logger.debug("SAl_ID=" + salId);
			}
		}

	}

	/**
	 * 两位数数字转化字符串补零，如：数字1转化后为“01”
	 * 
	 * @return
	 */
	private String numberToString(int number) {
		String numberString = String.valueOf(number);
		if (number < 10) {
			numberString = "0" + numberString;
		}
		return numberString;
	}

	/**
	 * 三位数数字转化字符串补零，如：数字1转化后为“001”
	 * 
	 * @return
	 */
	private String numberToStringNew(int number) {
		String numberString = String.valueOf(number);
		if (number < 10) {
			numberString = "00" + numberString;
		} else if (number < 100) {
			numberString = "0" + numberString;
		}
		return numberString;
	}

}
