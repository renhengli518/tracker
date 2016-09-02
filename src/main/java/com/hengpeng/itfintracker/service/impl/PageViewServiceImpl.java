package com.hengpeng.itfintracker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.dao.UserBehaviorRecordMapper;
import com.hengpeng.itfintracker.entity.UserBehaviorRecord;
import com.hengpeng.itfintracker.service.PageViewService;

/**
 * 用户行为主表服务接口实现类
 * 
 * @author renhengli
 */
@Service(value = "pageViewService")
public class PageViewServiceImpl implements PageViewService {

	@Autowired
	private UserBehaviorRecordMapper pageViewDao;

	public int savePageViewInfoToDB(UserBehaviorRecord pageView) {
		return this.pageViewDao.insert(pageView);
	}

	public List<UserBehaviorRecord> getUserBehaviorBySessionId(String sessionid) {
		return this.pageViewDao.getUserBehaviorBySessionId(sessionid);
	}

	public Page getUserBehaviorRecordPageList(Page page) {
		List<UserBehaviorRecord> list = this.pageViewDao.getUserBehaviorRecordPageList(page);
		page.setData(list);
		return page;
	}
}
