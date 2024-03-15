package com.sisp.dao;

import com.sisp.dao.entity.ProjectEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuestionnaireEntityMapper {
    //Add Questionnaire Prototype
    int createQuestionnaire(QuestionnaireEntity questionnaire);
    int modifyQuestionnaire(QuestionnaireEntity questionnaire);
    List<QuestionnaireEntity> getQuestionnaireByProject(int projectId);

    void deleteQuestionnaireLogicallyById(int qstId);
}
