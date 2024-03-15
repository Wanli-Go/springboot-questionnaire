package com.sisp.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ProblemDTO {
    private String problemName;
    private boolean mustAnswer;
    private List<OptionDTO> option;
    private String type;
    private String leftTitle;
    private String answer;
}
