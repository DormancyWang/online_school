package com.guo.eduservice;


import com.guo.eduservice.entity.vo.CourseInfoVo;
import com.guo.eduservice.service.EduCourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {

    @Autowired
    EduCourseService service;


    @Test
    public void test1(){
        System.out.println(service);
        service.saveCourseInfo(new CourseInfoVo());
    }
}
