package com.prb.controller;


import com.prb.entity.User;
import com.prb.pojo.AppResult;
import com.prb.service.ResultService;
import com.prb.vo.addVo.UserAddVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@Api(value = "操作结果 controller",tags = {"user controller"})
@Slf4j
public class ResultController {


    @Autowired
    ResultService resultService;

    @PostMapping("/add")
    @ApiOperation(value = "add user",notes = "添加user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名",paramType = "query",required = true,defaultValue = "大傻逼"),
            @ApiImplicitParam(name = "age",value = "年龄",paramType = "query",defaultValue = "22"),
            @ApiImplicitParam(name = "sex",value = "性别",paramType = "query",defaultValue = "0"),
            @ApiImplicitParam(name = "email",value = "邮箱",paramType = "query",required = true)})
    public AppResult addUser(String userName,Integer age,Integer sex,String email){

        log.info("调用 add user");
        return AppResult.SUCCESS(resultService.addUser(userName,age,sex,email));
    }

    @DeleteMapping("/del/{userId}")
    @ApiOperation(value = "del user",notes = "删除 user")
    public AppResult delUser(@PathVariable Long userId){
        log.info("调用 delUser");
        return AppResult.SUCCESS(resultService.delUser(userId));
    }


    @PutMapping("/up")
    @ApiOperation(value = "up user",notes = "更新 user")
    public AppResult upUser(@RequestBody UserAddVo user){
        log.info("调用 upUser");
        return AppResult.SUCCESS(resultService.upUser(user));
    }

    @GetMapping("/get/{userId}")
    @ApiOperation(value = "get user",notes = "根据id 获取user")
    public AppResult getUserById(@PathVariable Long userId){
        log.info("调用 getUserById");
        return AppResult.SUCCESS(resultService.getUserById(userId));
    }

    @GetMapping("/get/name/{userName}")
    @ApiOperation(value = "get user use name",notes = "通过name 获取user")
    public AppResult getUserByName(@PathVariable String userName){
        log.info("调用 getUserByName");
        return AppResult.SUCCESS(resultService.getUserByName(userName));
    }

    @GetMapping("/get/sex/{sex}")
    @ApiOperation(value = "page user use sex",notes = "根据性别分页获取 user")
    public AppResult pageUserForSex(@PathVariable Integer sex,
                                    @RequestParam(defaultValue = "1",name = "page") Integer page,
                                    @RequestParam(defaultValue = "10",name = "size") Integer size){
        log.info("调用 pageUserForSex");
        return AppResult.SUCCESS(resultService.pageUserForSex(sex,page,size));
    }


}
