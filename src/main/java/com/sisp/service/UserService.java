package com.sisp.service;

import com.sisp.dao.UserEntityMapper;
import com.sisp.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserEntityMapper userEntityMapper;

    @Autowired
    public UserService(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    //Add User
    public int addUserInfo(UserEntity user){
        int resultUser = userEntityMapper.insert(user);
        return resultUser!=0?resultUser:3;
    }
    //Modify User
    public int modifyUserInfo(UserEntity user){
        return userEntityMapper.updateByPrimaryKeySelective(user);
    }
    //Delete User
    public int deleteUserById(UserEntity user){
        return userEntityMapper.deleteByPrimaryKey(user.getId());
    }
    //Get All User List
    public List<UserEntity> queryUserList(UserEntity user){
        return userEntityMapper.queryUserList(user);
    }
    public List<UserEntity> queryAllUser(){
        return userEntityMapper.queryAllUser();
    }
}
