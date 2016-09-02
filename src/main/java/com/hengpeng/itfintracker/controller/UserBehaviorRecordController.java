package com.hengpeng.itfintracker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.service.PageViewService;

@Controller
@RequestMapping("userBehaviorRecord")
public class UserBehaviorRecordController {

	private static final Logger logger = Logger.getLogger(UserBehaviorRecordController.class);

	@Autowired
	private PageViewService pageViewService;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView userBehaviorRecord(HttpServletRequest request,ModelAndView md) {
		md.setViewName("userBehaviorRecordList");
		return md; 
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Page getUserBehaviorRecordPageList(HttpServletRequest request, String date_start, String date_end, String viewType, Page page) {
		logger.debug("查询用户行为分页信息开始");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(date_start)){
			map.put("date_start", date_start);
		}
		if(StringUtils.isNotEmpty(viewType)){
			map.put("viewType", viewType);
		}
		if(StringUtils.isNotEmpty(date_end)){
			map.put("date_end", date_end);
		}
		page.setMap(map);
		page = pageViewService.getUserBehaviorRecordPageList(page);
		logger.debug("查询用户行为分页信息结束");
		return page; 
	}
}
