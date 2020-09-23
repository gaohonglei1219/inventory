package com.admin.mapper;

import java.util.List;

import com.admin.entity.MonitorBean;

public interface MonitorMapper {
	
	/**
	 * @Date: 2017年8月8日下午3:18:49
	 * @User: linkaitao
	 * @Desc: 保存
	 */
	public int saveMonitorBean(MonitorBean monitorBean);
	
	/**
	 * @Date: 2017年8月8日下午4:09:18
	 * @User: linkaitao
	 * @Desc: 更新
	 */
	public void updateMonitor(MonitorBean monitorBean);
	
	/**
	 * @Date:2017年8月8日下午3:19:33
	 * @User:linkaitao
	 * @Desc: 查询列表
	 */
	public List<MonitorBean> findMonitorList();
	
	/**
	 * 
	 * @Date: 2017年8月8日下午3:22:22
	 * @User: linkaitao
	 * @Desc: 根据id查找
	 */
	public MonitorBean findMonitorById(int morId);
	
	
	
}
