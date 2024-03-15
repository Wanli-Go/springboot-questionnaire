package com.sisp.dao;

import com.sisp.dao.entity.ProjectEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component
public interface ProjectEntityMapper {
    //Search for User
    public List<ProjectEntity> queryProjectList(ProjectEntity project);
    //Add Data
    public int insert(ProjectEntity project);
    //Delete Data
    public int deleteProjectById(ProjectEntity project);
    //Modify Data
    public int modifyProjectInfo(ProjectEntity project);
    public List<ProjectEntity> queryAllProjects();
    ProjectEntity getDetailedProjectInfo(int id);
}
