package com.direct.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.direct.mapper.AdminMapper;
import com.direct.model.AttachImageVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class TaskTest {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Test
	public void taskTests() {
		
		List<AttachImageVO> fileList = adminMapper.checkFile();
		
		log.info("fileList........" + fileList);
		
		List<Path> checkFilePath = new ArrayList<Path>();
		
		log.info("111=======================================");
		
		
		fileList.forEach(vo -> {
			
			Path path = Paths.get("C:\\CYJ_PRO\\upload_file", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);

			path = Paths.get("C:\\CYJ_PRO\\upload_file", vo.getUploadPath(), "s_" +  vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
			
		});
		
		log.info("222=======================================");
		
		checkFilePath.forEach(list -> { 
			
			log.info("list = {}" , list); 

		});
		
		log.info("333=======================================");

		File targetDir = Paths.get("C:\\CYJ_PRO\\upload_file", getFolderYesterDay()).toFile();
		
		File[] targetFile = targetDir.listFiles();
		
		for(File file : targetFile) {
			System.out.println("~~~필터전~~" +file);
		}
		
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));
		
		for(File file : targetFile) {
			checkFilePath.forEach(checkFile -> {
				if(file.toPath().equals(checkFilePath)) {
					removeFileList.remove(file);
				}
			});
		}
		
		for(File file : removeFileList) {
			System.out.println("~~~필터후~~" +file);
		}
		
		//파일 삭제
		for(File file : removeFileList) {
			System.out.println("삭제: " + file);
			file.delete();
		}
		
		
	}
	
	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		System.out.println(str);
		
		return str.replace("-", File.separator);
	}

}
