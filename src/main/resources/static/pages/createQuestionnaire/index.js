onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建问卷')
  let projectId = sessionStorage.getItem('currentProjectId');

  // Fetch the list of projects here...
  $.ajax({
    url: API_BASE_URL + '/queryProjectList',
    type: "POST",
    data: JSON.stringify({createdBy: $util.getItem('userInfo').username}),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      // Dynamically populate the project select options
      const selectProject = $('#selectProject');
      selectProject.html('<option value="0" disabled hidden>请选择</option>');
      res.data.forEach(item => {
        let option = $(`<option value="${item.id}">${item.projectName}</option>`);
        if (item.id === projectId) {
          option.attr('selected', 'selected');
        }
        selectProject.append(option);
      })
    }
  })
}


const onCreateTemplate = () => {
  let projectId = $('#selectProject').val();
  let surveyType = $('#selectSurveyType').val();
  sessionStorage.removeItem('currentProjectId');
  sessionStorage.setItem('currentProjectId', projectId);
  sessionStorage.setItem('surveyType', surveyType);
  location.href = "/pages/createNewQuestionnaire/index.html"
}

const importHistoryQuestionnaire = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div>页面测试数据</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const surveyTypeTemplate = () => {
  $('#divider').css('display', 'flex')
  $('#templateB').html('')
  $('#templateB').append(`
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">创建模板</div>
          <div>题库抽题，限时作答，成绩查询，自动阅卷</div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="createTemplate()">创 建</button>
      </div>
    </div>
    <div class="template-item">
      <div class="item-t">
        <img class="img" src="../../static/images/blank_template.png">
        <div>
          <div class="title">测试</div>
          <div></div>
        </div>
      </div>
      <div class="item-b">
        <button type="button" class="btn btn-default" onclick="handleEdit()" style="margin-right: 10px;">编 辑</button>
        <button type="button" class="btn btn-default">导 入</button>
      </div>
    </div>
  `)
}

const createTemplate = () => {
  $('#createTemplateModal').modal('show')
}

const handleEdit = () => {
  open('/pages/designQuestionnaire/index.html')
}
