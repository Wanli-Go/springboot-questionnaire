package com.sisp.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private String id;
    private String username;
    private String password;
    private String roleId;
    private String startTime;
    private String stopTime;
    private String status;
    private String createdBy;
    private String creationDate;
    private String lastUpdatedBy;
    private String lastUpdateDate;
}
