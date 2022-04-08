package com.yzy.controller;

import com.yzy.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yzy.service.ITaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 任务 前端控制器
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
@Api(description = "任务相关")
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    ITaskService taskService;

    @ApiOperation("所有信息")
    @GetMapping("/test")
    public Result test() {
        return Result.succ(taskService.list(),0) ;
    }
}
