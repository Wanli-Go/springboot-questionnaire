package com.sisp.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QstInfoDTO {
    private String id;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private List<ProblemDTO> problems;
    private List<AnswerDTO> answersList;
}
