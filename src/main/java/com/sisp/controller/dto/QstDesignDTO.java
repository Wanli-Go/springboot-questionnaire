package com.sisp.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class QstDesignDTO {
    private String id;
    private String projectId;
    private String type;
    private String title;
    private String description;
    private List<ProblemDTO> problems;
}

