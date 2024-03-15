let projectId;
onload = () => {
  $('#headerDivB').text('项目详情')

  projectId = $util.getPageParam('seeProject')
  console.log(projectId, 'projectId')
  fetchProjectInfo(projectId)
  getQuestionnaireInfo()
}

const fetchProjectInfo = (id) => {
  $.ajax({
    url: API_BASE_URL + '/detailedProjectInfo',
    type: "POST",
    data: JSON.stringify(id),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data[0]
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.creationDate)
      $('#projectDescription').text(info.projectContent)
      $('#personInCharge').text(info.createdBy)
    }
  })
}

function getQuestionnaireInfo() {
  $.ajax({
    url: API_BASE_URL + `/getAnswers`,
    type: 'POST',
    dataType: 'json',
    data: projectId,
    contentType: 'application/json',
    success: (res) => {
      const answerTable = $('#questionnaire-table tbody');
      answerTable.empty();
      res.data.map(qstInfo => {
        console.log(qstInfo)
        let row = $(`<tr><td>${qstInfo.id}</td><td>${qstInfo.title}</td><td>${qstInfo.startDate}</td><td>${qstInfo.endDate}</td><td>     
            <button type="button" class="btn btn-link" onclick="preview('${encodeURIComponent(JSON.stringify(qstInfo.problems))}')">预览</button>
            <button type="button" class="btn btn-link" onclick="generateHTML('${encodeURIComponent(JSON.stringify(qstInfo.problems))}')">发布</button>
            <button type="button" class="btn btn-link btn-red" onclick="logicalDelete('${encodeURIComponent(JSON.stringify(qstInfo))}')">删除</button>
            <button type="button" class="btn btn-link btn-red" onclick="showStatistics('${encodeURIComponent(JSON.stringify(qstInfo))}')">统计</button></td></tr>`);
        answerTable.append(row)
      });
    },
    error: (err) => {
      console.error(err);
      // Handle error here
    }
  });
}

const generateHTML = (problems) => {
  localStorage.setItem('problems', decodeURIComponent(problems));
  alert(API_BASE_URL + "/pages/designQuestionnaire/modules/answerSheetModal/index.html")
}

const preview = (problems) => {
  localStorage.setItem('problems', decodeURIComponent(problems));
  const modal = document.getElementById('surveyModal');
  const iframe = document.getElementById('surveyIframe');

  // Retrieve survey questions and generate survey preview HTML dynamically

  // Set the source of the iframe to the survey preview page with necessary query parameters
  iframe.src = '/pages/designQuestionnaire/modules/answerSheetModal/index.html';

  // Show the modal using Bootstrap's modal() method
  $(modal).modal('show');

  $(modal).on('hidden.bs.modal', function() {
    // Clear the iframe source when the modal is closed
    iframe.src = '';
  });
}

const logicalDelete = (encodedQstInfo) => {
  const qstInfo = JSON.parse(decodeURIComponent(encodedQstInfo));
  const qstId = qstInfo.id;
  let currentDate = new Date();

  let otherDate = new Date(qstInfo.endDate);

  if (currentDate < otherDate) {
    alert("问卷不可删除因为尚在结束日期前。");
  } else {
    $.ajax({
      url: API_BASE_URL + `/deleteQuestionnaire`,
      type: 'POST',
      dataType: 'json',
      data: qstId,
      contentType: 'application/json',
      success: (res) => {
        alert("删除成功!");
        location.reload();
      },
      error: (err) => {
        console.error(err);
        // Handle error here
      }
    });
  }
}

const showStatistics = (encodedJsonQstInfoString) => {
  sessionStorage.setItem("qstInfo", decodeURIComponent(encodedJsonQstInfoString));
  location.href = "/pages/questionnaireStatistics/index.html";
}