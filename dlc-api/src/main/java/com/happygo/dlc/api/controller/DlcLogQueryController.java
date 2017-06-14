/**
 * Copyright  2017
 * 
 * All  right  reserved.
 *
 * Created  on  2017年6月5日 上午10:13:54
 *
 * @Package com.happygo.dlc.api  
 * @Title: DlcLogQueryController.java
 * @Description: DlcLogQueryController.java
 * @author sxp (1378127237@qq.com) 
 * @version 1.0.0 
 */
package com.happygo.dlc.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.happgo.dlc.base.bean.DlcLog;
import com.happygo.dlc.biz.service.DlcLogQueryService;
import com.happygo.dlc.common.entity.DlcLogResult;
import com.happygo.dlc.common.entity.helper.DlcLogResultHelper;

/**
 * ClassName:DlcLogQueryController
 * 
 * @Description: DlcLogQueryController.java
 * @author sxp (1378127237@qq.com)
 * @date:2017年6月5日 上午10:13:54
 */
@RestController
@RequestMapping("/dlc")
public class DlcLogQueryController {
	
	/**
	 * Logger the LOGGER 
	 */
	private static final Logger LOGGER = LogManager.getLogger();
	
	/** 
	* The field dlcLogQueryService
	*/
	@Autowired
	private DlcLogQueryService dlcLogQueryService;
	
	/**
	 * @MethodName: logQuery
	 * @Description: the method logQuery
	 * @param keyWord
	 * @return String
	 */
	@GetMapping(value = "/log/query", produces = { "application/json" })
	public ModelAndView logQuery(
			@RequestParam(value = "keyWord") String keyWord) {
		LOGGER.info("^------- DLC 日志查询开始，keyWord:[" + keyWord + "] -------^");
		long startTime = System.currentTimeMillis();
		List<DlcLog> queryDlcLogs = dlcLogQueryService.logQuery(keyWord.trim());
		long endTime = System.currentTimeMillis();
		long searchTime = endTime - startTime;
		DlcLogResult dlcLogResult = DlcLogResultHelper.buildDlcLogResult(
				keyWord, searchTime, queryDlcLogs);
		ModelAndView modelAndView = new ModelAndView("search_results");
		modelAndView.addObject("dlcLogResult", dlcLogResult);
		LOGGER.info("^------- DLC 日志查询结束  -------^");
		return modelAndView;
	}
}
