package com.yzy.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yzy.common.Result;
import com.yzy.entity.User;
import com.yzy.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
@Api(description = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation("登录")
    @PostMapping("/login")
    @ResponseBody
    public Result login(String code) {
        String appid = "wx0fbfa1fcbf756d9e";
        String secret = "5d23321887243d2fec25505193c90760";
        String wx = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
            + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code", String.class);

        JSONObject js = JSON.parseObject(wx);
        String openid = js.getString("openid");
        String session_key = js.getString("session_key");
        Result result = getScore(openid);

        if (result.getCode() == 200) {
            // 查询成功 返回信息
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("openid", openid);
            jsonObject.put("score", result.getData());
            return Result.succ(jsonObject, 1);
        } else {
            // 新增用户
            java.util.Date date = new Date();// 获得当前时间
            Timestamp timestamp = new Timestamp(date.getTime());
            User user = new User();
            user.setOpenid(openid);
            user.setScore(BigDecimal.valueOf(0));
            user.setChangeTime(timestamp);
            Result res = add(user);
            if (res.getCode() == 200) {
                return Result.succ(js, 1);
            } else {
                return Result.fail("新增用户失败");
            }
        }
    }

    @ApiOperation("新增用户")
    @GetMapping("/add")
    @ResponseBody
    public Result add(@RequestBody User request) {

        try {
            if (userService.save(request)) {
                return Result.succ(null, 1);
            } else {
                return Result.fail("新增用户失败");
            }
        } catch (Exception e) {
            return Result.fail("新增用户异常");
        }
    }

    @ApiOperation("查询用户积分")
    @PostMapping("/getscore")
    @ResponseBody
    public Result getScore(String openid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("_openid", openid);
        try {
            User user = userService.getOne(queryWrapper);
            if (user != null) {
                return Result.succ(user.getScore(), 1);
            } else {
                return Result.fail("该用户不存在");
            }
        } catch (Exception e) {
            return Result.fail("查询积分异常");
        }
    }

}
