package com.sisp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sisp.bean.HttpResponseEntity;
import com.sisp.common.utils.ResponseGenerator;
import com.sisp.controller.ProjectController;
import com.sisp.controller.QuestionnaireController;
import com.sisp.controller.UserController;
import com.sisp.controller.dto.QstInfoDTO;
import com.sisp.dao.*;
import com.sisp.dao.entity.*;
import com.sisp.service.ProjectService;
import com.sisp.service.QuestionnaireService;
import com.sisp.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.sisp.controller.dto.OptionDTO;
import com.sisp.controller.dto.ProblemDTO;
import com.sisp.controller.dto.QstDesignDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.CollectionUtils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import java.io.InputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;
@SpringBootTest
class UserMapperTests {
//    @Test
//    void contextLoads() {

    //    }
    Logger log = Logger.getLogger(UserMapperTests.class);
    @Test
    public void queryUserList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        List<UserEntity> list = userEntityMapper.queryAllUser();
        if(!CollectionUtils.isEmpty(list)){
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

    @Test
    public void selectUserInfo() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setPassword("123");
        List<UserEntity> list = userEntityMapper.queryUserList(userEntity);
        if(!CollectionUtils.isEmpty(list)){
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>qselectUserInfo用户登录测试成功");
        }
    }

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setStatus("1");
        userEntity.setUsername("aaaaa");
        userEntity.setPassword("123");
        int i = userEntityMapper.insert(userEntity);
        if(i!=0){
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert用户插入测试成功");
        }
    }

    //@Test
    public void adeleteUserByName() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserEntityMapper userEntityMapper = sqlSession.getMapper(UserEntityMapper.class);
        //调用userMapper的方法
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("SuperAdmin");
        int i = userEntityMapper.deleteUserByName(userEntity);
        if(i!=0){
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete用户删除测试成功");
        }
    }
}

class ProjectServiceTest {
    @Mock
    private ProjectEntityMapper projectEntityMapper;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addProjectInfo() {
        when(projectEntityMapper.insert(any(ProjectEntity.class))).thenReturn(1);

        int result = projectService.addProjectInfo(new ProjectEntity());
        assertEquals(1, result);

        verify(projectEntityMapper, times(1)).insert(any(ProjectEntity.class));
    }

    @Test
    void modifyProjectInfo() {
        when(projectEntityMapper.modifyProjectInfo(any(ProjectEntity.class))).thenReturn(1);

        int result = projectService.modifyProjectInfo(new ProjectEntity());
        assertEquals(1, result);

        verify(projectEntityMapper, times(1)).modifyProjectInfo(any(ProjectEntity.class));
    }

    @Test
    void deleteProjectById() {
        when(projectEntityMapper.deleteProjectById(any(ProjectEntity.class))).thenReturn(1);

        int result = projectService.deleteProjectById(new ProjectEntity());
        assertEquals(1, result);

        verify(projectEntityMapper, times(1)).deleteProjectById(any(ProjectEntity.class));
    }

    @Test
    void queryUserList() {
        List<ProjectEntity> expectedList = Arrays.asList(new ProjectEntity(), new ProjectEntity());
        when(projectEntityMapper.queryProjectList(any(ProjectEntity.class))).thenReturn(expectedList);

        List<ProjectEntity> resultList = projectService.queryUserList(new ProjectEntity());
        assertEquals(expectedList, resultList);

        verify(projectEntityMapper, times(1)).queryProjectList(any(ProjectEntity.class));
    }

    @Test
    void queryAllProjects() {
        List<ProjectEntity> expectedList = Arrays.asList(new ProjectEntity(), new ProjectEntity());
        when(projectEntityMapper.queryAllProjects()).thenReturn(expectedList);

        List<ProjectEntity> resultList = projectService.queryAllProjects();
        assertEquals(expectedList, resultList);

        verify(projectEntityMapper, times(1)).queryAllProjects();
    }

    @Test
    public void testProjectDetails() {
        String projectId = "123";
        ProjectEntity expected = new ProjectEntity();
        when(projectEntityMapper.getDetailedProjectInfo(Integer.parseInt(projectId))).thenReturn(expected);

        assertEquals(expected, projectService.projectDetails(projectId));
    }

}

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserEntityMapper userEntityMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAddUserInfo() {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userEntityMapper.insert(user)).thenReturn(1);

        assertEquals(1, userService.addUserInfo(user));
        verify(userEntityMapper, times(1)).insert(user);
    }

    @Test
    public void testDeleteUserInfo() {
        UserEntity user = new UserEntity();
        user.setId("1");

        when(userEntityMapper.deleteByPrimaryKey("1")).thenReturn(1);

        assertEquals(1, userService.deleteUserById(user));
        verify(userEntityMapper, times(1)).deleteByPrimaryKey("1");
    }

    @Test
    public void testModifyUserInfo() {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userEntityMapper.updateByPrimaryKeySelective(user)).thenReturn(1);

        assertEquals(1, userService.modifyUserInfo(user));
        verify(userEntityMapper, times(1)).updateByPrimaryKeySelective(user);
    }

    @Test
    public void testQueryAllUser() {

        when(userEntityMapper.queryAllUser()).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), userService.queryAllUser());
        verify(userEntityMapper, times(1)).queryAllUser();
    }

    @Test
    public void testQueryUserInfo() {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        ArrayList<UserEntity> returnedUserList = new ArrayList<>();
        returnedUserList.add(user);

        ArrayList<UserEntity> expectedUserList = (ArrayList<UserEntity>)returnedUserList.clone();

        when(userEntityMapper.queryUserList(user)).thenReturn(returnedUserList);

        assertEquals(expectedUserList, userService.queryUserList(user));
        verify(userEntityMapper, times(1)).queryUserList(user);
    }
}


@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLogin() throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userService.queryUserList(any(UserEntity.class))).thenReturn(Arrays.asList(user));

        mockMvc.perform(post("/admin/userLogin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddUserInfo() throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userService.addUserInfo(any(UserEntity.class))).thenReturn(1);

        mockMvc.perform(post("/admin/addUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testQueryUserList() throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userService.queryAllUser()).thenReturn(Arrays.asList(user));

        mockMvc.perform(post("/admin/queryUserList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyUserInfo() throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userService.modifyUserInfo(any(UserEntity.class))).thenReturn(1);

        mockMvc.perform(post("/admin/modifyUserInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUserInfo() throws Exception {
        UserEntity user = new UserEntity();
        user.setUsername("Test");

        when(userService.deleteUserById(any(UserEntity.class))).thenReturn(1);

        mockMvc.perform(post("/admin/deleteUserinfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testRespondPostPositive() {
        HttpResponseEntity response = ResponseGenerator.respondPost("200", "400", "Success", "Failed", 1);

        assertEquals("200", response.getCode());
        assertEquals("Success", response.getMessage());
        assertEquals(1, response.getData());
    }

    @Test
    public void testRespondPostNegative() {
        HttpResponseEntity response = ResponseGenerator.respondPost("200", "400", "Success", "Failed", 0);

        assertEquals("400", response.getCode());
        assertEquals("Failed", response.getMessage());
        assertEquals(0, response.getData());
    }

    @Test
    public void testRespondGetListPositive() {
        HttpResponseEntity response = ResponseGenerator.respondGetList("200", "400", "Success", "Failed", Arrays.asList("Item1", "Item2"));

        assertEquals("200", response.getCode());
        assertEquals("Success", response.getMessage());
        assertTrue(response.getData() instanceof List);
    }

    @Test
    public void testRespondGetListNegative() {
        HttpResponseEntity response = ResponseGenerator.respondGetList("200", "400", "Success", "Failed", Collections.emptyList());

        assertEquals("400", response.getCode());
        assertEquals("Failed", response.getMessage());
        assertEquals(0, response.getData());
    }

}

class ProjectControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    void addUserInfo() throws Exception {
        when(projectService.addProjectInfo(any(ProjectEntity.class))).thenReturn(1);

        mockMvc.perform(post("/addProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("添加成功！"));
    }

    @Test
    void queryProjectList() throws Exception {
        List<ProjectEntity> projectList = Arrays.asList(new ProjectEntity(), new ProjectEntity());
        when(projectService.queryAllProjects()).thenReturn(projectList);

        mockMvc.perform(post("/queryProjectList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("查询成功"));
    }

    @Test
    void modifyProjectInfo() throws Exception {
        when(projectService.modifyProjectInfo(any(ProjectEntity.class))).thenReturn(1);

        mockMvc.perform(post("/modifyProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("修改成功"));
    }

    @Test
    void deleteProjectById() throws Exception {
        when(projectService.deleteProjectById(any(ProjectEntity.class))).thenReturn(1);

        mockMvc.perform(post("/deleteProjectById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("删除成功"));
    }

    @Test
    public void testGetDetailedProjectInfo() throws Exception {
        when(projectService.projectDetails(anyString())).thenReturn(new ProjectEntity());

        mockMvc.perform(post("/detailedProjectInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("查询成功"));
    }
}


class QuestionnaireServiceTest {
    @Mock
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Mock
    private ProblemEntityMapper problemEntityMapper;

    @Mock
    private AnswerEntityMapper answerEntityMapper;

    private QuestionnaireService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new QuestionnaireService(questionnaireEntityMapper, problemEntityMapper, answerEntityMapper);
    }

    @Test
    public void testCreateQuestionnaire() {
        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setId("1");
        when(questionnaireEntityMapper.createQuestionnaire(any())).thenReturn(1);
        int result = service.createQuestionnaire(questionnaireEntity);
        assertEquals(1, result);
    }

    @Test
    public void testModifyQuestionnaire() {
        QstDesignDTO questionnaire = new QstDesignDTO();
        questionnaire.setTitle("Title");
        ProblemDTO problemDTO = new ProblemDTO();
        problemDTO.setProblemName("Problem");
        OptionDTO optionDTO1, optionDTO2;
        optionDTO1 = new OptionDTO();
        optionDTO1.setChooseTerm("1");
        optionDTO2 = new OptionDTO();
        optionDTO2.setFraction("1");
        List<OptionDTO> optionDTOList = new ArrayList<>();
        optionDTOList.add(optionDTO1);
        optionDTOList.add(optionDTO2);
        problemDTO.setOption(optionDTOList);
        questionnaire.setProblems(Arrays.asList(problemDTO));
        when(questionnaireEntityMapper.modifyQuestionnaire(any(QuestionnaireEntity.class))).thenReturn(1);
        doNothing().when(problemEntityMapper).insertProblem(any());
        int result = service.modifyQuestionnaire(questionnaire);
        assertEquals(1, result);
    }


    @Test
    public void testGetAnswersByProjectService() {
        // Set up the test
        String projectId = "1";

        QuestionnaireEntity entity = new QuestionnaireEntity();
        entity.setId("2");
        List<QuestionnaireEntity> questionnaireEntities = Arrays.asList(entity);

        // Assume getQuestionnaireByProjectId returns a list of QuestionnaireEntity
        when(service.getQuestionnaireByProjectId("1")).thenReturn(questionnaireEntities);
        ProblemEntity problem = new ProblemEntity();
        problem.setOptions("1,2");
        when(problemEntityMapper.getProblemsByQstId(anyInt())).thenReturn(Arrays.asList(problem));
        AnswerEntity answer = new AnswerEntity();
        answer.setAnswers("0#1");
        when(answerEntityMapper.selectByQstId(anyString())).thenReturn(Arrays.asList(answer));

        // Call the method to test
        List<QstInfoDTO> result = service.getAnswersByProjectService(projectId);

        // Verify interactions with the mocked objects
        verify(problemEntityMapper, times(questionnaireEntities.size())).getProblemsByQstId(anyInt());
        verify(answerEntityMapper, times(questionnaireEntities.size())).selectByQstId(anyString());

        // (Optionally) verify the results
        assertEquals(questionnaireEntities.size(), result.size());
    }

    @Test
    public void testDeleteQuestionnaire() {
        String qstId = "123";

        int result = service.deleteQuestionnaire(qstId);

        assertEquals(1, result);
        verify(questionnaireEntityMapper, times(1)).deleteQuestionnaireLogicallyById(Integer.parseInt(qstId));
    }

}


class QuestionnaireControllerTest {
    @Mock
    private QuestionnaireService service;

    @InjectMocks
    private QuestionnaireController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new QuestionnaireController(service);
    }

    @Test
    public void testCreateQuestionnaire() {
        when(service.createQuestionnaire(any())).thenReturn(1);
        HttpResponseEntity response = controller.createQuestionnaire(new QuestionnaireEntity());
        assertEquals("200", response.getCode());
        assertEquals("添加成功！", response.getMessage());
    }

    @Test
    public void testModifyQuestionnaire() {
        when(service.modifyQuestionnaire(any())).thenReturn(1);
        HttpResponseEntity response = controller.modifyQuestionnaire(new QstDesignDTO());
        assertEquals("200", response.getCode());
        assertEquals("添加成功！", response.getMessage());
    }

    @Test
    public void testGetAnswersByQuestionnaire() throws Exception {
        List<QstInfoDTO> results = new ArrayList<>(); // Fill with some test data
        results.add(new QstInfoDTO());

        when(service.getAnswersByProjectService(anyString())).thenReturn(results);

        HttpResponseEntity response = controller.getAnswersByQuestionnaire("1");
        assertEquals("200", response.getCode());
        assertEquals("查询成功！", response.getMessage());
    }

    @Test
    public void testDeleteQuestionnaire(){
        when(service.deleteQuestionnaire(anyString())).thenReturn(1);
        HttpResponseEntity response = controller.deleteQuestionnaire("1");
        assertEquals("200", response.getCode());
        assertEquals("删除成功！", response.getMessage());
    }


}

