package com.guo.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guo.commutils.R;
import com.guo.eduservice.entity.EduTeacher;
import com.guo.eduservice.entity.vo.TeacherQuery;
import com.guo.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author guo
 * @since 2022-05-09
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService service;

    @ApiOperation("所有的讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = service.list(null);
        R items = R.ok().data("items", list);
        return items;
    }


    @ApiOperation("删除讲师")
    @DeleteMapping("/{id}")
    public R removeTeacher(@ApiParam(name="id",value = "讲师的id") @PathVariable("id") String id) {
        boolean b = service.removeById(id);
        if(b){
            return R.ok();
        }else{
            return R.error();
        }
    }


    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable("current") Integer current,@PathVariable("limit")Integer limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        IPage<EduTeacher> page1 = service.page(page, null);
        long total = page1.getTotal();
        List<EduTeacher> records = page1.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }



    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,
                                  @PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(level!=null){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        IPage<EduTeacher> page1 = service.page(page, wrapper);
        long total = page1.getTotal();
        List<EduTeacher> records = page1.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher teacher){
        boolean save = service.save(teacher);
        if(save) return R.ok();
        return R.error();
    }

    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable Long id){
        EduTeacher teacher = service.getById(id);
        if(teacher == null) return R.error();
        return R.ok().data("teacher",teacher);
    }

    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = service.updateById(eduTeacher);
        if(b) return R.ok();
        return R.error();
    }
}

