package com.cmbchina.activity.tran.restful.common.controller;

//import com.cmbchina.commons.bean.SystemProperties;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by wangtingbang on 16/4/10.
 */
@Controller
@RequestMapping(value = "upload")
public class FileUploadController {

  private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

  private String NGINX_BASE_DIR;
  private String NGINX_PIC_DIR;

  @RequestMapping(value="file")
  @ResponseBody
  public String uploadPicToNginx(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
    if(StringUtils.isEmpty(NGINX_PIC_DIR)){
      NGINX_BASE_DIR = "/usr/local/nginx/";
      NGINX_PIC_DIR = "html/activity-files/";//SystemProperties.get("nginx.pic.dir"); //TODO
    }
    log.info("开始, file == null?:{}", file==null);
    String path = NGINX_BASE_DIR+NGINX_PIC_DIR;//request.getSession().getServletContext().getRealPath("upload");
    String fileName = file.getOriginalFilename();
    log.info("file path:{}", path);
    File targetFile = new File(path, fileName);
    if(!targetFile.exists()){
      targetFile.mkdirs();
    }

    //保存
    try {
      file.transferTo(targetFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return NGINX_PIC_DIR.endsWith("/")?NGINX_PIC_DIR+fileName:NGINX_PIC_DIR+"/"+fileName;
  }
}