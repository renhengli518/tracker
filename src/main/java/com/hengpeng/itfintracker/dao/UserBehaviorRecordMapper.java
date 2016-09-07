package com.hengpeng.itfintracker.dao;

import java.util.List;
import java.util.Map;

import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.entity.UserBehaviorRecord;

public interface UserBehaviorRecordMapper {

	int insert(UserBehaviorRecord record);

	int insertSelective(UserBehaviorRecord record);

	List<UserBehaviorRecord> getUserBehaviorByIp(String ip);

	List<UserBehaviorRecord> getUserBehaviorRecordPageList(Page page);

	List<UserBehaviorRecord> getUserBehaviorRecordList(Map<String, Object> map);
}