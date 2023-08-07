package com.direct.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.direct.mapper.AdminMapper;
import com.direct.model.AttachImageVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AttachFileCheckTask {
	
	@Autowired
	private AdminMapper adminMapper;
	
	//매일 새벽1시 
	@Scheduled(cron = "0 0 1 * * *") 
	public void checkFile() throws Exception{
		
		log.warn("File Check Task Run......");
		log.warn(null, new Date());
		log.warn("=========================");
		
		//DB에 저장된 파일 리스트
		List<AttachImageVO> fileList = adminMapper.checkFile();
		
		//비교기준 파일 리스트(Path 객체)
		List<Path> checkFilePath = new ArrayList<Path>();
		
		fileList.forEach(vo -> { //원본 이미지
			Path path = Paths.get("C:\\CYJ_PRO\\upload_file", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		fileList.forEach(vo ->{ //썸네일 이미지
			Path path = Paths.get("C:\\CYJ_PRO\\upload_file", vo.getUploadPath(), "s_" + vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		//디렉토리 파일 리스트
		File targetDir = Paths.get("C:\\CYJ_PRO\\upload_file", getFolderYesterDay()).toFile();
		File[] targetFile = targetDir.listFiles();
		
		//삭제대상 파일 제거
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));
		
		for(File file : targetFile) {
			checkFilePath.forEach(checkFile -> {
				if(file.toPath().equals(checkFile)) {
					removeFileList.add(file);
				}
			});
		}
		
		//삭제 대상 파일 제거
		log.warn("file Delete...");
		for(File file : removeFileList) {
			
			log.warn("target file = {} ", file);
			file.delete();
		}
		
		
	}
	
	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
		
	}
	

}
