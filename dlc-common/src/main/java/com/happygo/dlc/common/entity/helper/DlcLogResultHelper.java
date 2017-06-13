/**
 * Copyright  2017
 * 
 * All  right  reserved.
 *
 * Created  on  2017年6月7日 下午12:49:06
 *
 * @Package com.happygo.dlc.common.entity.helper  
 * @Title: DlcLogResultHelper.java
 * @Description: DlcLogResultHelper.java
 * @author sxp (1378127237@qq.com) 
 * @version 1.0.0 
 */
package com.happygo.dlc.common.entity.helper;

import java.util.ArrayList;
import java.util.List;

import com.happgo.dlc.base.bean.DlcLog;
import com.happygo.dlc.common.DateConstants;
import com.happygo.dlc.common.entity.DlcLogBaseResult;
import com.happygo.dlc.common.entity.DlcLogResult;
import com.happygo.dlc.common.util.DateUtils;

/**
 * ClassName:DlcLogResultHelper
 * 
 * @Description: DlcLogResultHelper.java
 * @author sxp (1378127237@qq.com)
 * @date:2017年6月7日 下午12:49:06
 */
public class DlcLogResultHelper {

	private DlcLogResultHelper() {}

	/**
	 * @MethodName: buildDlcLogResult
	 * @Description: the method buildDlcLogResult
	 * @return DlcLogResult
	 */
	public static DlcLogResult buildDlcLogResult(String keyWord, long searchTime,
			List<DlcLog> dlcLogs) {
		DlcLogResult dlcLogResult = new DlcLogResult();
		dlcLogResult.setKeyWord(keyWord);
		dlcLogResult.setSearchTime(String.valueOf(searchTime/1000.0));
		if (dlcLogs == null || dlcLogs.isEmpty()) {
			dlcLogResult.setDlcLogBaseResults(new ArrayList<DlcLogBaseResult>(0));
			return dlcLogResult;
		}
		List<DlcLogBaseResult> baseResults = new ArrayList<DlcLogBaseResult>();
		for (final DlcLog dlcLog : dlcLogs) {
			baseResults.add(buildDlcLogBaseResult(dlcLog));
		}
		dlcLogResult.setDlcLogBaseResults(baseResults);
		return dlcLogResult;
	}

	/**
	 * @MethodName: buildDlcLogBaseResult
	 * @Description: the method buildDlcLogBaseResult
	 * @param dlcLog
	 * @param searchTime
	 * @return DlcLogBaseResult
	 */
	private static DlcLogBaseResult buildDlcLogBaseResult(DlcLog dlcLog) {
		DlcLogBaseResult baseResult = new DlcLogBaseResult();
		baseResult.setContent(dlcLog.getContent());
		baseResult.setHostIP(dlcLog.getHostIp());
		baseResult.setLogRecordTime(DateUtils.date2String(dlcLog.getTime(),
				DateConstants.YYYY_MM_DD_HH_MM_SSS));
		baseResult.setSystemName(dlcLog.getSystemName());
		return baseResult;
	}
}
