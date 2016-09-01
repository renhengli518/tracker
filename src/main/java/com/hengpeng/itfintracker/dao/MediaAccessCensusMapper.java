package com.hengpeng.itfintracker.dao;

import java.util.List;
import java.util.Map;

import com.hengpeng.itfintracker.entity.MediaAccessCensus;

public interface MediaAccessCensusMapper {
	
	int insert(MediaAccessCensus record);

	int insertSelective(MediaAccessCensus record);

	List<MediaAccessCensus> getMediaAccessCensus(Map<String,Object> map);

	List<MediaAccessCensus> getMediaAccessCensusByTime(String date);
}