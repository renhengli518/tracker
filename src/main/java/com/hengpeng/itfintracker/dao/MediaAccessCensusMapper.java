package com.hengpeng.itfintracker.dao;

import com.hengpeng.itfintracker.entity.MediaAccessCensus;

public interface MediaAccessCensusMapper {
    int insert(MediaAccessCensus record);

    int insertSelective(MediaAccessCensus record);
}