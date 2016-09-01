package com.hengpeng.itfintracker.service;

import java.util.List;

import com.hengpeng.itfintracker.dto.MediaTypeReportDto;
import com.hengpeng.itfintracker.entity.MediaAccessCensus;

public interface ReportService {
	List<MediaAccessCensus> getDailyMediaReport(String startdate,String endDate) throws Exception;
	
	List<MediaTypeReportDto> getDailyMediaReportGroupByMediaType(String startdate,String endDate) throws Exception;
	
	List<MediaTypeReportDto> getMediaAccessCensusByTime(String date);
}
