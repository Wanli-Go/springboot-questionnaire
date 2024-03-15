package com.sisp.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class QuestionnaireEntity {
    private String id;
    private String projectId;
    private String type;
    private String qstName;
    private String qstContent;
    private String startDate;
    private String endDate;
}
