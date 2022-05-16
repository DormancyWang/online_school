package com.guo.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadVideoAly(MultipartFile file);


    //delete multiple video
    public void removeMoreAlyVideo(List videoIdList);




}
