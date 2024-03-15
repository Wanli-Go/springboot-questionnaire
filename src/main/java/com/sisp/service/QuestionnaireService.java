package com.sisp.service;

import com.sisp.controller.dto.*;
import com.sisp.dao.AnswerEntityMapper;
import com.sisp.dao.ProblemEntityMapper;
import com.sisp.dao.QuestionnaireEntityMapper;
import com.sisp.dao.entity.AnswerEntity;
import com.sisp.dao.entity.ProblemEntity;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.dao.entity.QuestionnaireEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuestionnaireService {
    private final QuestionnaireEntityMapper questionnaireEntityMapper;
    private final ProblemEntityMapper problemEntityMapper;
    private final AnswerEntityMapper answerEntityMapper;

    @Autowired
    public QuestionnaireService(QuestionnaireEntityMapper questionnaireEntityMapper, ProblemEntityMapper problemEntityMapper, AnswerEntityMapper answerEntityMapper) {
        this.questionnaireEntityMapper = questionnaireEntityMapper;
        this.problemEntityMapper = problemEntityMapper;
        this.answerEntityMapper = answerEntityMapper;
    }

    public int createQuestionnaire(QuestionnaireEntity questionnaire){
        questionnaireEntityMapper.createQuestionnaire(questionnaire);
        return Integer.parseInt(questionnaire.getId());
    }

    public int modifyQuestionnaire(QstDesignDTO questionnaire){
        //Update Questionnaire Info
        QuestionnaireEntity updatedQuestionnaire = new QuestionnaireEntity();
        updatedQuestionnaire.setId(questionnaire.getId());
        updatedQuestionnaire.setProjectId(questionnaire.getProjectId());
        updatedQuestionnaire.setQstName(questionnaire.getTitle());
        updatedQuestionnaire.setType(questionnaire.getType());
        updatedQuestionnaire.setQstContent(questionnaire.getDescription());
        //System.out.println(updatedQuestionnaire);
        questionnaireEntityMapper.modifyQuestionnaire(updatedQuestionnaire);

        //Update Problems Info
        List<ProblemDTO> problemDTOs = questionnaire.getProblems();
        for (ProblemDTO problemDTO: problemDTOs){
            ProblemEntity newProblem = new ProblemEntity();
            newProblem.setQstId(questionnaire.getId());
            newProblem.setType(problemDTO.getType());
            newProblem.setName(problemDTO.getProblemName());
            newProblem.setLeftTitle(problemDTO.getLeftTitle());
            newProblem.setMustAnswer(problemDTO.isMustAnswer());
            String optionString = null;
            String optionValueString = null;
            int flag = 0;
            for (OptionDTO option: problemDTO.getOption()){
                flag ++;
                if (flag == 1){
                    optionString = "";
                    optionString += option.getChooseTerm();
                    if(option.getFraction()!=null){
                        optionValueString = "";
                        optionValueString += option.getFraction();
                    }
                }else {
                    optionString = optionString + "," + option.getChooseTerm();
                    if (optionValueString != null) {
                        optionValueString = optionValueString + "," + option.getFraction();
                    }
                }
            }
            newProblem.setOptions(optionString);
            newProblem.setOptionValues(optionValueString);

            //System.out.println(newProblem);
            problemEntityMapper.insertProblem(newProblem);
        }

        return 1;
    }

    public List<QuestionnaireEntity> getQuestionnaireByProjectId(String projectId){
        int parsedId = Integer.parseInt(projectId);
        return questionnaireEntityMapper.getQuestionnaireByProject(parsedId);
    }

    /*
    Returns List of QstInfoDTO:
    String id;
    String participant;
    String answers;
    String date;
    String title;
    String description;
    List<ProblemDTO> problems;
    */
    public List<QstInfoDTO> getAnswersByProjectService(String project_id){
        ArrayList<QstInfoDTO> result = new ArrayList<>();

        //get all questionnaires under this projectId
        List<QuestionnaireEntity> questionnaireEntities = getQuestionnaireByProjectId(project_id);
        System.out.println(questionnaireEntities);
        for (QuestionnaireEntity qst: questionnaireEntities) {

            //Set Up Title & Description
            QstInfoDTO qstInfo = new QstInfoDTO();
            qstInfo.setId(qst.getId());///
            qstInfo.setTitle(qst.getQstName());///
            qstInfo.setStartDate(qst.getStartDate());
            qstInfo.setDescription(qst.getQstContent());///
            qstInfo.setEndDate(qst.getEndDate());///

            //Set Up ProblemDTOs
            List<ProblemDTO> problems = new ArrayList<>();
            List<ProblemEntity> problemEntities = problemEntityMapper.getProblemsByQstId(Integer.parseInt(qst.getId()));
            for (ProblemEntity problemEntity : problemEntities) {
                ProblemDTO problemDTO = new ProblemDTO();
                problemDTO.setType(problemEntity.getType());
                problemDTO.setProblemName(problemEntity.getName());
                problemDTO.setLeftTitle(problemEntity.getLeftTitle());
                problemDTO.setMustAnswer(problemEntity.isMustAnswer());

                String optionString = problemEntity.getOptions();
                String optionValueString = problemEntity.getOptionValues();

                String[] options = optionString.split(",");
                String[] optionValues = null;
                if(optionValueString != null) optionValues = optionValueString.split(",");

                List<OptionDTO> optionDTOs = new ArrayList<>();
                for (int i = 0; i < options.length; i++) {
                    OptionDTO optionDTO = new OptionDTO();
                    optionDTO.setChooseTerm(options[i]);
                    if(optionValueString != null) optionDTO.setFraction(optionValues[i]);
                    optionDTOs.add(optionDTO);
                }
                problemDTO.setOption(optionDTOs);
                problems.add(problemDTO);
            }
            qstInfo.setProblems(problems);


            List<AnswerEntity> answerEntities = answerEntityMapper.selectByQstId(qst.getId());
            List<AnswerDTO> answerDTOs = new ArrayList<>();
            for(AnswerEntity answerEntity: answerEntities){
                AnswerDTO answer = new AnswerDTO();
                answer.setDate(answerEntity.getDate());////
                answer.setParticipant(answerEntity.getParticipant());////
                String[] strings = answerEntity.getAnswers().split("#");
                ArrayList<String> answers = new ArrayList<>(Arrays.asList(strings));
                answer.setAnswers(answers);////
                answerDTOs.add(answer);
            }
            qstInfo.setAnswersList(answerDTOs);///

            result.add(qstInfo);
        }

        return result;
    }

    public int deleteQuestionnaire(String qstId) {
        questionnaireEntityMapper.deleteQuestionnaireLogicallyById(Integer.parseInt(qstId));
        return 1;
    }
}
