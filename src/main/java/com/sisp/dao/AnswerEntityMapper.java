package com.sisp.dao;

import com.sisp.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AnswerEntityMapper {
    List<AnswerEntity> selectByQstId(String qstId);
}

