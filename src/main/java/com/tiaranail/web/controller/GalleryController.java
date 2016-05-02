package com.tiaranail.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.makeit.common.util.file.FileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tiaranail.web.domain.Gallery;
import com.tiaranail.web.domain.Video;
import com.tiaranail.web.service.GalleryServiceImpl;

@Controller
public class GalleryController {

	Gallery gallery;
	@Autowired
	GalleryServiceImpl galleryService;

	@RequestMapping(value = "goGalleryAdd.tiara", method = RequestMethod.GET)
	public String goMenu(Model model) {
		model.addAttribute("list", galleryService.getGalleryTotalList());
		return "gallery";
	}

	@RequestMapping(value = "galleryTotalList.tiara", method = RequestMethod.GET)
	public @ResponseBody List<Gallery> getGelleryTotalList() {
		return galleryService.getGalleryTotalList();
	}

	@RequestMapping(value = "addGallery.tiara", method = RequestMethod.POST)
	public String addGallery(@RequestParam Map<String,String> map,
			HttpServletRequest request, @RequestParam("file") MultipartFile uploadfile) {
		FileUpload fileUpLoad = new FileUpload();

		gallery = new Gallery();
		gallery.setgFileName(uploadfile.getOriginalFilename());

		String fileurl = fileUpLoad.uploadImageFileWithThumbnail(uploadfile);

		String thumbNail = thumbnail(fileurl);
		
		gallery.setgFileUrl(fileurl.replace("/var/lib/tomcat6/webapps/ROOT", ""));
		gallery.setgThumbNail(thumbNail.replace("/var/lib/tomcat6/webapps/ROOT", ""));
		gallery.setgTitle(map.get("gTitle"));
		gallery.setgBody(map.get("gBody"));
		galleryService.addGallery(gallery);
		
		return "redirect:goGalleryAdd.tiara";
	}

	private String thumbnail(String loadFile) {
		String suffixChar = "_t";
		String extension = getFileExtension(loadFile);
		int dot = loadFile.lastIndexOf('.');
		String target = loadFile.substring(0, dot);

		return target + suffixChar + "." + extension;
	}

	private String getFileExtension(String fileName) {
		int dotPosition = fileName.lastIndexOf('.');

		if (-1 != dotPosition && fileName.length() - 1 > dotPosition) {
			return fileName.substring(dotPosition + 1);
		} else {
			return "";
		}
	}
	
	@RequestMapping(value = "getVideoList.tiara", method = RequestMethod.GET)
	public @ResponseBody List<Video> getVideoList() {
		return galleryService.getVideoList();
	}
	//병주 추가
	@RequestMapping(value = "youtube.tiara", method = RequestMethod.GET)
	public String youtube(Model model) {
		List<Video> list = galleryService.getVideoList();
		model.addAttribute("video",list);
		return "gallery";
	}

	//병주수정
	@RequestMapping(value = "addVideo.tiara", method = RequestMethod.POST)
	public String addVideo(@ModelAttribute Video paramMap) {
		galleryService.addVideo(paramMap);
		return "redirect:youtube.tiara";
	}
	@RequestMapping(value = "galleryRemove.tiara", method = RequestMethod.GET)
	public String galleryRemove(@RequestParam Map<String,String> paramMap) {
		gallery = new Gallery();
		gallery.setgNum(paramMap.get("gNum"));
		System.out.println(paramMap.get("gNum"));
		galleryService.removeGall(gallery);
		return "redirect:goGalleryAdd.tiara";
	}
	@RequestMapping(value = "videoRemove.tiara", method = RequestMethod.GET)
	public String videoRemove(@ModelAttribute Video paramMap) {
		galleryService.removeVideo(paramMap);
		return "redirect:youtube.tiara";
	}
}
