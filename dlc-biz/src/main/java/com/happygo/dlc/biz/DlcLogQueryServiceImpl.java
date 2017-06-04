/**
* Copyright  2017
* 
* All  right  reserved.
*
* Created  on  2017年6月4日 上午9:25:42
*
* @Package com.happygo.dlc.biz  
* @Title: DlcLogQueryServiceImpl.java
* @Description: DlcLogQueryServiceImpl.java
* @author sxp (1378127237@qq.com) 
* @version 1.0.0 
*/
package com.happygo.dlc.biz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;

import com.happgo.dlc.base.DlcLog;
import com.happygo.dlc.dal.DlcLogQueryCallback;

/**
 * ClassName:DlcLogQueryServiceImpl
 * @Description: DlcLogQueryServiceImpl.java
 * @author sxp (1378127237@qq.com) 
 * @date:2017年6月4日 上午9:25:42
 */
public class DlcLogQueryServiceImpl implements DlcLogQueryService {
	
	/**
	 * Ignite the ignite 
	 */
	@IgniteInstanceResource
	private Ignite ignite;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happygo.dlc.biz.DlcLogQueryService#logQuery(java.lang.String)
	 */
	public List<DlcLog> logQuery(String keyWord) {
		Collection<List<DlcLog>> logQueryResults = ignite.compute().broadcast(
				new DlcLogQueryCallback(keyWord));
		if (logQueryResults == null) {
			return null;
		}
		List<DlcLog> logQueryDlcLogs = new ArrayList<DlcLog>();
		for (Iterator<List<DlcLog>> it = logQueryResults.iterator(); it
				.hasNext();) {
			logQueryDlcLogs.addAll(it.next());
		}
		return logQueryDlcLogs;
	}
}
