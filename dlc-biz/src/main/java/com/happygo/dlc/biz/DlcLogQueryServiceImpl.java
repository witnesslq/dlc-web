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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happgo.dlc.base.DlcConstants;
import com.happgo.dlc.base.bean.DlcLog;
import com.happygo.dlc.biz.service.DlcLogQueryService;
import com.happygo.dlc.dal.access.DlcLogQueryCallback;

/**
 * ClassName:DlcLogQueryServiceImpl
 * @Description: DlcLogQueryServiceImpl.java
 * @author sxp (1378127237@qq.com) 
 * @date:2017年6月4日 上午9:25:42
 */
@Service
public class DlcLogQueryServiceImpl implements DlcLogQueryService {
	
	/**
	 * Ignite the ignite 
	 */
	@Autowired
	private Ignite ignite;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.happygo.dlc.biz.DlcLogQueryService#logQuery(java.lang.String)
	 */
	public List<DlcLog> logQuery(String keyWord) {
		Collection<List<DlcLog>> logQueryResults = ignite.compute().broadcast(
				new DlcLogQueryCallback(keyWord, null));
		if (logQueryResults == null) {
			return null;
		}
		List<DlcLog> logQueryDlcLogs = new ArrayList<DlcLog>();
		for (Iterator<List<DlcLog>> it = logQueryResults.iterator(); it
				.hasNext();) {
			logQueryDlcLogs.addAll(it.next());
		}
		if (!logQueryDlcLogs.isEmpty()) {
			return logQueryDlcLogs;
		}
		
		//如果完全匹配有结果，就无需在进行相似度查询
		logQueryResults = ignite.compute().broadcast(
				new DlcLogQueryCallback(keyWord, DlcConstants.DLC_MORE_LIKE_THIS_QUERY_MODE));
		if (logQueryResults == null) {
			return null;
		}
		for (Iterator<List<DlcLog>> it = logQueryResults.iterator(); it
				.hasNext();) {
			logQueryDlcLogs.addAll(it.next());
		}
		return logQueryDlcLogs;
	}
}
