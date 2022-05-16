package com.guo.oss.controller;

import com.guo.commutils.R;
import com.guo.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService service;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = service.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
