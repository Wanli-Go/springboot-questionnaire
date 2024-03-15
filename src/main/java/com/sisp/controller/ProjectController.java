package com.sisp.controller;

import com.sisp.bean.HttpResponseEntity;
import com.sisp.common.utils.ResponseGenerator;
import com.sisp.dao.entity.ProjectEntity;
import com.sisp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/addProjectInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody ProjectEntity project){
        int result = projectService.addProjectInfo(project);
        return ResponseGenerator.respondPost("200","405","添加成功！","添加失败！",result);
    }

    @RequestMapping(value = "/queryProjectList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(){
        List<ProjectEntity> result = projectService.queryAllProjects();
        return ResponseGenerator.respondGetList("200","405","查询成功","查询失败",result);
    }

    @RequestMapping(value = "/modifyProjectInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity project){
        int result = projectService.modifyProjectInfo(project);
        return ResponseGenerator.respondPost("200","405","修改成功", "修改失败", result);
    }

    @RequestMapping(value = "/deleteProjectById", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity project){
        int result = projectService.deleteProjectById(project);
        return ResponseGenerator.respondPost("200","405","删除成功", "删除失败", result);
    }
    @RequestMapping(value = "/detailedProjectInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity getDetailedProjectInfo(@RequestBody String projectId){
        System.out.println(projectId);
        projectId = projectId.replace("\"","");
        List<ProjectEntity> result = Arrays.asList(projectService.projectDetails(projectId));
        return ResponseGenerator.respondGetList("200","405","查询成功","查询失败",result);
    }
}
