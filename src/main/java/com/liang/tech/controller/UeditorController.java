package com.liang.tech.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.liang.tech.ueditor.ConfigJson;
import com.liang.tech.ueditor.Ueditor;

@RestController
public class UeditorController {
	
	
	
	
	@RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return ConfigJson.UEDITOR_CONFIG;
    }

	@RequestMapping(value="/imgUpload")
    @ResponseBody
    public String imgUpload(MultipartFile upfile,HttpServletRequest request) throws IOException {
		//System.out.println(upfile);
        Ueditor ueditor = new Ueditor();
        
        
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //win路径
       // String pathImg=path.getParentFile().getParentFile().getParent()+File.separator+"uploadFile"+File.separator+"/ueditor/image";
        //linux路径
        String pathImg="/uploadFile"+File.separator+"/ueditor/image";
        
        //pathImg=pathImg.substring(3,pathImg.length());
        //System.out.println("图片目录："+pathImg);
        
        String Suffix = upfile.getOriginalFilename();
        int pos = Suffix.lastIndexOf(".");
        String extName = Suffix.substring(pos+1).toLowerCase();
       // System.out.println("文件名："+extName);
        //String pathImg = request.getSession().getServletContext().getRealPath("/upload/ueditor/image");
        
    	/*String ct = upfile.getContentType() ;
		String fileType = "";
		if (ct.indexOf("/")>0) {
			fileType = ct.substring(ct.indexOf("/")+1);
		}
		System.out.println("文件类型："+fileType);*/
		String fileName = UUID.randomUUID() + "." + extName;
		
		File targetFile = new File(pathImg);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		File targetFile2 = new File(pathImg+"/"+fileName);
		if (!targetFile2.exists()) {
			targetFile2.createNewFile();
		}
		// 保存
		upfile.transferTo(targetFile2);
		
		ueditor.setState("SUCCESS");
		ueditor.setTitle(fileName);
		ueditor.setOriginal(fileName);
		ueditor.setUrl("/ueditor/image"+File.separator+fileName);
		return JSON.toJSONString(ueditor);
    }
	
	@RequestMapping(value="/fileUpload")
    @ResponseBody
    public String fileUpload(MultipartFile upfile,HttpServletRequest request) throws IOException {
		//System.out.println("文件："+upfile);
        Ueditor ueditor = new Ueditor();
        
        String Suffix = upfile.getOriginalFilename();
        int pos = Suffix.lastIndexOf(".");
        String extName = Suffix.substring(pos+1).toLowerCase();
        System.out.println("文件名："+extName);
        
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //win路径
       // String pathFile=path.getParentFile().getParentFile().getParent()+File.separator+"uploadFile"+File.separator+"/ueditor/file";
        //pathImg=pathImg.substring(3,pathImg.length());
        //linux路径
        String pathFile="/uploadFile"+File.separator+"/ueditor/file";
        
        System.out.println("文件目录："+pathFile);
        
		String fileName = UUID.randomUUID() + "." + extName;
		File targetFile = new File(pathFile);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		File targetFile2 = new File(pathFile+"/"+fileName);
		if (!targetFile2.exists()) {
			targetFile2.createNewFile();
		}
		// 保存
		upfile.transferTo(targetFile2);
		
		ueditor.setState("SUCCESS");
		ueditor.setTitle(fileName);
		ueditor.setOriginal(fileName);
		ueditor.setUrl("/ueditor/file"+File.separator+fileName);
		return JSON.toJSONString(ueditor);
    }
	@RequestMapping(value="/videoUpload")
    @ResponseBody
    public String videoUpload(MultipartFile upfile,HttpServletRequest request) throws IOException {
		System.out.println(upfile);
        Ueditor ueditor = new Ueditor();
        
        String Suffix = upfile.getOriginalFilename();
        int pos = Suffix.lastIndexOf(".");
        String extName = Suffix.substring(pos+1).toLowerCase();
        System.out.println("文件名："+extName);
        
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        //win路径
       // String pathFile=path.getParentFile().getParentFile().getParent()+File.separator+"uploadFile"+File.separator+"/ueditor/file";
        //pathImg=pathImg.substring(3,pathImg.length());
        //linux路径
        String pathFile="/uploadFile"+File.separator+"/ueditor/videos";
        
        System.out.println("文件目录："+pathFile);
        
		String fileName = UUID.randomUUID() + "." + extName;
		File targetFile = new File(pathFile);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		File targetFile2 = new File(pathFile+"/"+fileName);
		if (!targetFile2.exists()) {
			targetFile2.createNewFile();
		}
		// 保存
		upfile.transferTo(targetFile2);
		
		ueditor.setState("SUCCESS");
		ueditor.setTitle(fileName);
		ueditor.setOriginal(fileName);
		ueditor.setUrl("/ueditor/videos"+File.separator+fileName);
		return JSON.toJSONString(ueditor);
    }
	


}
