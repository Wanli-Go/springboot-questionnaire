package com.sisp.controller;

import com.sisp.bean.HttpResponseEntity;
import com.sisp.common.utils.ResponseGenerator;
import com.sisp.dao.entity.UserEntity;
import com.sisp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity login(@RequestBody UserEntity user){
        List<UserEntity> userEntities = userService.queryUserList(user);
        return ResponseGenerator.respondGetList("666","405","登陆成功","登陆失败", userEntities);
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity user){
        user.setStatus("1");
        int result = userService.addUserInfo(user);
        return ResponseGenerator.respondPost("666","405","添加成功","添加失败",result);
    }

    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity query(@RequestBody UserEntity user){
        List<UserEntity> result = userService.queryAllUser();
        return ResponseGenerator.respondGetList("666","405","查询成功","查询失败",result);
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity user){
        int result = userService.modifyUserInfo(user);
        return ResponseGenerator.respondPost("666","405","修改成功","修改失败",result);
    }

    @RequestMapping(value = "/deleteUserinfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUserInfo(@RequestBody UserEntity user){
        int result = userService.deleteUserById(user);
        return ResponseGenerator.respondPost("666","405","删除成功","删除失败",result);
    }
}
