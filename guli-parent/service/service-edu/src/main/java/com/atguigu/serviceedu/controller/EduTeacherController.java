package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.DataResult;
import com.atguigu.serviceedu.controller.entity.EduTeacher;
import com.atguigu.serviceedu.controller.entity.query.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xiaobai
 * @since 2022-05-12
 */
@RestController
@RequestMapping("/serviceedu/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @GetMapping("findAll")
    public DataResult findAllTeacher() {
        int i = 10/0;
        List<EduTeacher> list = teacherService.list(null);
        DataResult dataResult = DataResult.ok().data("items", list);
        return dataResult;
    }

    @DeleteMapping("{id}")
    public DataResult removeById(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return DataResult.ok();
        } else {
            return DataResult.error();
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{size}")
    public DataResult pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "size", value = "每页记录数", required = true)
            @PathVariable Long size) {
        Page<EduTeacher> pageParam = new Page<>(page, size);

        teacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        Long total = pageParam.getTotal();
        return DataResult.ok().data("total", total).data("rows", records);
    }

    //条件查询 带分页

    @PostMapping("pageTeacherCondition/{page}/{size}")
    public DataResult pageTeacherCondition(@PathVariable Long page, @PathVariable Long size,
                                           @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(page, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }

        teacherService.page(pageTeacher, wrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        Long total = pageTeacher.getTotal();
        return DataResult.ok().data("total", total).data("rows", records);
    }

    @PostMapping("addTeacher")
    public DataResult save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.save(eduTeacher);
        if (flag) {
            return DataResult.ok();
        } else {
            return DataResult.error();
        }
    }

    @GetMapping("getTeacherById/{id}")
    public DataResult getTeacherById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return DataResult.ok().data("rows",teacher);
    }

    @PostMapping("updateTeacher")
    public DataResult updateTeacher(@RequestBody EduTeacher teacher){
        boolean flag = teacherService.updateById(teacher);
        if (flag) {
            return DataResult.ok();
        } else {
            return DataResult.error();
        }
    }
}

