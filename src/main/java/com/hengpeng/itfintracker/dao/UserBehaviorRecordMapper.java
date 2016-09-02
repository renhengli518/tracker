package com.hengpeng.itfintracker.dao;

import java.util.List;

import com.hengpeng.itfintracker.commons.page.Page;
import com.hengpeng.itfintracker.entity.UserBehaviorRecord;

public interface UserBehaviorRecordMapper {

	int insert(UserBehaviorRecord record);

	int insertSelective(UserBehaviorRecord record);

	List<UserBehaviorRecord> getUserBehaviorBySessionId(String sessionid);

	List<UserBehaviorRecord> getUserBehaviorRecordPageList(Page page);

}