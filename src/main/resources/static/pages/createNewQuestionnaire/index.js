onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建调查问卷')

  $('#startTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })
  $('#endTime').datetimepicker({
    language: 'zh-CN', // 显示中文
    format: 'yyyy-mm-dd', // 显示格式
    minView: "month", // 设置只显示到月份
    initialDate: new Date(), // 初始化当前日期
    autoclose: true, // 选中自动关闭
    todayBtn: true // 显示今日按钮
  })

  $(".btn-primary").click(function() {
    // Create an object based on user input
    let questionnaireEntity = {
      projectId: sessionStorage.getItem('currentProjectId'),
      type: sessionStorage.getItem('surveyType'),
      qstName: $('#surveyName').val(),
      qstContent: $('#surveyDescription').val(),
      startDate: $('#startTime').find("input").val(),
      endDate: $('#endTime').find("input").val(),
    };

    //store surveyName & description to sessionStorage
    sessionStorage.setItem("surveyName", questionnaireEntity.qstName);
    sessionStorage.setItem("surveyDescription", questionnaireEntity.qstContent);

    // Send a POST request to the server
    $.ajax({
      url: API_BASE_URL + '/createQuestionnaire', // Replace with your actual API base URL
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(questionnaireEntity),
      success: function(res) {
        // On success, redirect to the next page
        if(res.code === '200'){
          sessionStorage.setItem("qstId", res.data);
        }else {
          console.error('Error creating project');
          alert(this.data);
        }
        window.location.href = "/pages/designQuestionnaire/index.html";
      },
    });
  });
}

