package com.hengpeng.itfintracker.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTaskTest {
	@Scheduled(cron = "0/5 * *  * * ? ") // 每5秒执行一次
	public void excute() {
		System.out.println("进入测试");
	}
	@Scheduled(cron = "0/5 * *  * * ? ") // 每5秒执行一次
	public void excute1(){
		System.out.println("1212");
	}
}
