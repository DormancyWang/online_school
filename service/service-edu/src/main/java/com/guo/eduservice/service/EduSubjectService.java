package com.guo.eduservice.service;

import com.guo.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author guo
 * @since 2022-05-12
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService service);


    //课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
