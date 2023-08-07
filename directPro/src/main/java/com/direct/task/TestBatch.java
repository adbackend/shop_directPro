package com.direct.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestBatch {
	
//	c(cron = "10 * * * * *")
	public void testMethod() throws Exception{
		
		log.warn("배치 실행 테스트..");
		log.warn("배치 실행 테스트..");
		
	}

}
