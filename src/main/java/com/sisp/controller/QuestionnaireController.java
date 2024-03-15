package com.sisp.controller;

import com.sisp.bean.HttpResponseEntity;
import com.sisp.common.utils.ResponseGenerator;
import com.sisp.controller.dto.QstDesignDTO;
import com.sisp.controller.dto.QstInfoDTO;
import com.sisp.dao.entity.AnswerEntity;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import com.sisp.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @RequestMapping(value = "/createQuestionnaire", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity createQuestionnaire(@RequestBody QuestionnaireEntity entity){
        int result = questionnaireService.createQuestionnaire(entity);
        return ResponseGenerator.respondPost("200","405","添加成功！","添加失败！",result);
    }

    @RequestMapping(value = "/modifyQuestionnaire", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaire(@RequestBody QstDesignDTO qstDesignDTO){
        int result = questionnaireService.modifyQuestionnaire(qstDesignDTO);
        return ResponseGenerator.respondPost("200","405","添加成功！","添加失败！",result);
    }

    @RequestMapping(value = "/getAnswers", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity getAnswersByQuestionnaire(@RequestBody String projectId){
        //System.out.println(projectId);
        projectId = projectId.replace("\"","");
        List<QstInfoDTO> result = questionnaireService.getAnswersByProjectService(projectId);
        System.out.println(result);
        return ResponseGenerator.respondGetList("200","405","查询成功！","查询失败！",result);
    }

    @RequestMapping(value = "/deleteQuestionnaire", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaire(@RequestBody String qstId){
        //System.out.println(projectId);
        qstId = qstId.replace("\"","");
        int result = questionnaireService.deleteQuestionnaire(qstId);
        System.out.println(result);
        return ResponseGenerator.respondPost("200","405","删除成功！","删除失败！",result);
    }
}
