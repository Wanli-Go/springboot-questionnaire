package com.sisp.dao;

import com.sisp.dao.entity.ProblemEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProblemEntityMapper {
    void insertProblem(ProblemEntity problem);
    List<ProblemEntity> getProblemsByQstId(int qstId);
}
