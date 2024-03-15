package com.sisp.service;

import com.sisp.dao.ProjectEntityMapper;
import com.sisp.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectEntityMapper projectEntityMapper;

    @Autowired
    public ProjectService(ProjectEntityMapper projectEntityMapper) {
        this.projectEntityMapper = projectEntityMapper;
    }

    //Add User
    public int addProjectInfo(ProjectEntity project){
        int resultUser = projectEntityMapper.insert(project);
        return resultUser!=0?resultUser:3;
    }
    //Modify User
    public int modifyProjectInfo(ProjectEntity project){
        return projectEntityMapper.modifyProjectInfo(project);
    }
    //Delete User
    public int deleteProjectById(ProjectEntity project){
        return projectEntityMapper.deleteProjectById(project);
    }
    //Get All User List
    public List<ProjectEntity> queryUserList(ProjectEntity project){
        return projectEntityMapper.queryProjectList(project);
    }
    public List<ProjectEntity> queryAllProjects(){
        return projectEntityMapper.queryAllProjects();
    }
    public ProjectEntity projectDetails(String projectId){
        return projectEntityMapper.getDetailedProjectInfo(Integer.parseInt(projectId));
    }
}
