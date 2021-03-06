package com.guo.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.eduservice.entity.frontvo.CourseFrontVo;
import com.guo.eduservice.entity.frontvo.CourseWebVo;
import com.guo.eduservice.entity.vo.CourseInfoVo;
import com.guo.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guo
 * @since 2022-05-13
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String,Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
