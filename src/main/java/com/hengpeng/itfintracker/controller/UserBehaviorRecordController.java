package com.hengpeng.itfintracker.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hengpeng.itfintracker.commons.constant.ReturnConstant;
import com.hengpeng.itfintracker.commons.model.ReturnResultUtil;
import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.commons.utils.RedisTemplateUtils;
import com.hengpeng.itfintracker.commons.utils.UserBehaviorExcelUtils;
import com.hengpeng.itfintracker.entity.UserBehaviorRecord;
import com.hengpeng.itfintracker.service.PageViewService;

@Controller
@RequestMapping("userBehaviorRecord")
public class UserBehaviorRecordController {

	private static final Logger logger = Logger.getLogger(UserBehaviorRecordController.class);

	@Autowired
	private PageViewService pageViewService;
	
	@Value("#{configProperties['export.url.base']}")
	private String exportPath;

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView userBehaviorRecord(HttpServletRequest request,ModelAndView md) {
		md.setViewName("userBehaviorRecordList");
		return md; 
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public Page getUserBehaviorRecordPageList(HttpServletRequest request, String date_start, String date_end, String viewType, Page page) {
		logger.debug("查询用户行为分页信息开始");
		Object o = RedisTemplateUtils.get("userBehavior.list."+date_start+"_"+date_end+"_"+viewType+"_"+page.getStart()+"_"+page.getEnd());
		//List<?> list = RedisTemplateUtils.lget("userBehavior_1.list."+date_start+"_"+date_end+"_"+viewType+"_"+page.getStart()+"_"+page.getEnd());
		if(o != null){
			//page.setData((List<UserBehaviorRecord>) o);
			page = (Page) o;
		}else{
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
			RedisTemplateUtils.set("userBehavior.list."+date_start+"_"+date_end+"_"+viewType+"_"+page.getStart()+"_"+page.getEnd(), page,60l);
			//RedisTemplateUtils.lpush("userBehavior_1.list."+date_start+"_"+date_end+"_"+viewType+"_"+page.getStart()+"_"+page.getEnd(), page.getData(),60l);
		}
		logger.debug("查询用户行为分页信息结束");
		return page; 
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "exportUserBehaviorRecordList", method = RequestMethod.GET)
	@ResponseBody
	public ReturnResultUtil exportUserBehaviorRecordList(HttpServletRequest request,HttpServletResponse response, String date_start, String date_end, String viewType) throws Exception {
		logger.debug("导出用户行为列表开始");
		List<UserBehaviorRecord> list = new ArrayList<UserBehaviorRecord>();
		Object o = RedisTemplateUtils.get("userBehavior.list.export."+date_start+date_end+viewType);
		if(o != null){
			//page.setData((List<UserBehaviorRecord>) o);
			list = (List<UserBehaviorRecord>) o;
		}else{
			
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
			list = pageViewService.getUserBehaviorRecordList(map);
			RedisTemplateUtils.set("userBehavior.list.export."+date_start+date_end+viewType, list,30l);
		}
		if(CollectionUtils.isNotEmpty(list)){
			String filePath = UserBehaviorExcelUtils.exportUserBehaviorRecordList(list, exportPath);
			//自动下载文件开始
			//request.getRequestDispatcher("/downLoadFileServlet?url="+filePath).forward(request, response);
			logger.debug("导出用户行为列表结束");
			return new ReturnResultUtil(ReturnConstant.RETURN_VALUE_00, "导出成功", filePath); 
		}else{
			return new ReturnResultUtil(ReturnConstant.RETURN_VALUE_01, "无需下载"); 
		}
	}
}
