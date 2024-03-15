let projects;
onload = () => {
    projects = JSON.parse(sessionStorage.getItem("projects"));
    const selectProject = $('#selectProject');
    projects.forEach(item => {
        let option = $(`<option value=${item.id}>${item.projectName}</option>`);
        if (item.id === sessionStorage.getItem("currentProjectId")) {
            option.attr('selected', 'selected');
        }
        selectProject.append(option);
    })
    getQuestionnaireStats();
};

function filterParticipants() {
    const searchText = $('#searchParticipant').val().toLowerCase();
    $('#answerHistoryTable tbody tr').filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(searchText) > -1)
    });
}

function getQuestionnaireStats() {
    const projectId = $("#selectProject").val();
    $.ajax({
        url: API_BASE_URL + `/getAnswers`, // Update with your API endpoint
        type: 'POST',
        dataType: 'json',
        data: projectId,
        contentType: 'application/json',
        success: (res) => {
            const answerTable = $('#answerHistoryTable tbody');
            answerTable.empty();
            res.data.map(qstInfo => {
                console.log(qstInfo)
                let answersList = qstInfo.answersList;
                answersList.map(answer => {
                    let row = $(`<tr><td>${qstInfo.title}</td><td>${answer.participant}</td><td>${answer.date}</td><td><button class="btn btn-primary" onclick="showAnswers('${encodeURIComponent(JSON.stringify(qstInfo))}','${encodeURIComponent(JSON.stringify(answer.answers))}')">显示回答</button></td></tr>`);
                    answerTable.append(row);
                })
            });
        },
        error: (err) => {
            console.error(err);
            // Handle error here
        }
    });
}

function showAnswers(qstInfo, participantAnswers) {
    sessionStorage.setItem('qstInfo', decodeURIComponent(qstInfo));
    sessionStorage.setItem('participantAnswers', decodeURIComponent(participantAnswers));
    console.log("problemSet");
    $('#answersModal').modal('show');
    document.getElementById('answersIframe').contentWindow.location.reload();
}
