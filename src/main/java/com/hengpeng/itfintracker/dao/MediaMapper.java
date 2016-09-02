package com.hengpeng.itfintracker.dao;

import com.hengpeng.itfintracker.entity.Media;

public interface MediaMapper {
    int insert(Media record);

    int insertSelective(Media record);
}