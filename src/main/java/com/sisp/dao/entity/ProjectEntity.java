package com.sisp.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEntity {
    private String id;
    private String projectName;
    private String userId;
    private String projectContent;
    private String createdBy;
    private String creationDate;
    private String lastUpdatedBy;
    private String lastUpdateDate;
}
