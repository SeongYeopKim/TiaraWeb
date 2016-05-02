package kr.co.makeit.common.util.file;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일업로드 commonIO사용
 * @since 2014. 9. 13.
 * @version 1.0
 * @author jinhyung
 * <pre>
 * @  수정일         수정자              수정내용
 * @ ---------    ---------   -------------------------------
 * @ 2014. 9. 13.	jinhyung			최초생성
 * @ 2014.10. 01.	jinhyung	썸네일 포함 이미지업로드 추가
 * </pre>
 * Copyright (C) by Make-IT All right reserved.
 */
@Component
public class FileUpload {
//	String rootPath = "/Users/jinhyung/Desktop/";
	String rootPath = "/var/lib/tomcat6/webapps/ROOT/resources/";
	
	/**
	 * images폴더에 이미지를 [년도/날짜]폴더에 저장하는 메서드
	 * @author jinhyung
	 * @date 2014. 9. 13.
	 * @param file Multipart파일
	 * @return 저장된 곳의 FileSystem경로
	 */
	public String uploadImageFileWithThumbnail(MultipartFile file) {
		String imageFilePath = "images/";							//이미지 파일의 저장위치							
		String originalFileName = file.getOriginalFilename();		//원본 파일 이름
		SimpleDateFormat newFileNameFormat = new SimpleDateFormat("HHmmssSS", java.util.Locale.KOREA);	//시분초의 시간값 포맷
		String newFileName = newFileNameFormat.format(new Date());	//시분초의 시간 문자열
		String extension = "." + getFileExtension(originalFileName);//원본파일의 확장자 (.포함)
		
		//파일이 저장될 실제 FileSystem 경로 + 파일명
		String targetFileName = plusFolderByDate(imageFilePath) + newFileName;
		
		targetFileName = fileSave(file, targetFileName, extension);								//파일저장
		create100by100Thumbnail(targetFileName);
		
		return targetFileName;
	}
	/**
	 * 100X100 썸네일 생성
	 * @author kimjinhyung
	 * @date 2014. 10. 2.
	 * @param loadFile 썸네일을 만들 파일 fullPath
	 */
	private void create100by100Thumbnail(String loadFile) {
		String suffixChar = "_t";
		String extension = getFileExtension(loadFile);
		int dot = loadFile.lastIndexOf('.');
		String target = loadFile.substring(0, dot);
		
		createThumbnail(loadFile, target + suffixChar + "." + extension, extension);
	}
	
	/**
	 * 파일을 저장하는 메서드
	 * @author jinhyung
	 * @date 2014. 9. 13.
	 * @param file MultipartFile
	 * @param fullFileName 파일의 실제 경로 및 확장자
	 * @return
	 */
	private String fileSave(MultipartFile file, String fullFileName, String extension) {
		int i = 0;
		FileOutputStream fos = null;
		File existCheck = null;
		String saveTarget = fullFileName + extension;
		try {
			byte fileData[] = file.getBytes();

			//중복검사 ( 중복되면 파일이름 뒤에 "_" 이랑 숫자 를 붙여줌
			existCheck = new File(saveTarget);
			while(existCheck.exists()) {
				i++;
				if(i==1)
					fullFileName = fullFileName + "_";
				saveTarget = fullFileName + i + extension;
				existCheck = new File(saveTarget);
			}
			
			//파일 저장
			fos = new FileOutputStream(saveTarget);
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {}
			}
		}
		return saveTarget;
	}
	
	/**
	 * 년도/일 폴더를 생성하고 반환하는 메서드
	 * @author jinhyung
	 * @date 2014. 9. 13.
	 * @param basePath 일반 Path
	 * @return 생성되고 생성된 Path가 반영된 Path
	 */
	private String plusFolderByDate(String basePath){
		SimpleDateFormat year = new SimpleDateFormat("yyyy", Locale.KOREA);
		SimpleDateFormat monthDay = new SimpleDateFormat("MMdd", Locale.KOREA);
		String folder = year.format(new Date()) + File.separator + monthDay.format(new Date()) + File.separator;
		
		String realPath = rootPath + basePath + folder;
		File newdir = new File(realPath);
		if(!newdir.exists())
			newdir.mkdirs();
		
		return rootPath + basePath + folder;	
	}
	/**
	 * 파일 이름으로부터 확장자를 리턴하는 메서드
	 * @author jinhyung
	 * @date 2014. 9. 13.
	 * @param fileName 파일이름(abc.png)
	 * @return 확장자 명(png)
	 */
	private String getFileExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');
		
		if (-1 != dotPosition && fileName.length() - 1 > dotPosition) {
			return fileName.substring(dotPosition + 1);
		} else {
			return "";
		}
	}
	
	/**
	 * 썸네일 생성 기본 메서드
	 * @author kimjinhyung
	 * @date 2014. 9. 30.
	 * @param loadFile 썸네일 생성할 파일 총 경로
	 * @param saveFile 썸네일 파일 총 경로
	 * @param extension 확장자
	 */
	private void createThumbnail(String loadFile, String saveFile, String extension) {
		File save = new File(saveFile);
		try {
			FileInputStream fis = new FileInputStream(loadFile);
			BufferedImage im = ImageIO.read(fis);
			
			int width = 150;		//im.getWidth() / x
			int height = 150;		//im.getHeight() / x
			
			BufferedImage thumb = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = thumb.createGraphics();
			
			g2.drawImage(im, 0, 0, width, height, null);
			ImageIO.write(thumb, extension, save);
		} catch (IOException e) {
			
		}
	}
}
