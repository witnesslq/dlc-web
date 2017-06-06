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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.happgo.dlc.base.DlcLog;
import com.happygo.dlc.biz.service.DlcLogQueryService;

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
	@GetMapping(value = "/log/query/{keyWord}", produces = {"application/json"})
	public ModelAndView logQuery(@PathVariable("keyWord") String keyWord) {
		List<DlcLog> queryDlcLogs = dlcLogQueryService.logQuery(keyWord);
		ModelAndView modelAndView = new ModelAndView("search_results");
		modelAndView.addObject("dlcLogs", queryDlcLogs);
		modelAndView.addObject("keyWord", keyWord);
		return modelAndView;
	}
}
