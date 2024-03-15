package com.sisp.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AnswerDTO {
    private String participant;
    private List<String> answers;
    private String date;
}
