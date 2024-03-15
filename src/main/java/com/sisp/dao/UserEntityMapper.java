package com.sisp.dao;

import com.sisp.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserEntityMapper {
    //Search for User
     List<UserEntity> queryUserList(UserEntity user);
    //Add Data
     int insert(UserEntity user);
    //Delete Data
     int deleteByPrimaryKey(String id);
     int deleteUserByName(UserEntity user);
    //Modify Data
     int updateByPrimaryKeySelective(UserEntity user);
     List<UserEntity> queryAllUser();
}
