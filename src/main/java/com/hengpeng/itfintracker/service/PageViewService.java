package com.hengpeng.itfintracker.service;

import java.util.List;
import java.util.Map;

import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.entity.UserBehaviorRecord;

/**
 * 用户行为服务接口
 * 
 * @author renhengli
 */
public interface PageViewService {

	public int savePageViewInfoToDB(UserBehaviorRecord behaviorUser);

	public List<UserBehaviorRecord> getUserBehaviorBySessionId(String sessionId);

	public Page getUserBehaviorRecordPageList(Page page);

	public List<UserBehaviorRecord> getUserBehaviorRecordList(Map<String, Object> map);
}
