package com.sisp.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ProblemEntity {
    private String qstId;
    private String type;
    private String name;
    private String options;
    private String optionValues;
    private String leftTitle;
    private boolean mustAnswer;
}