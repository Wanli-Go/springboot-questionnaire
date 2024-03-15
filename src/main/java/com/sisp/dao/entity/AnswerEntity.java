package com.sisp.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class AnswerEntity {
    private String qstId;
    private String participant;
    private String answers;
    private String date;
}