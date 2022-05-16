package com.guo.eduservice.service;

import com.guo.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guo.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author guo
 * @since 2022-05-13
 */
public interface EduChapterService extends IService<EduChapter> {

    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    //删除章节的方法
    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
