let qstInfo;
onload = () => {
    $('#headerDivB').text('问卷统计')

    qstInfo = JSON.parse(sessionStorage.getItem('qstInfo'));
    console.log(qstInfo, 'qstInfo')

    fetchQstInfo(qstInfo)
}

const fetchQstInfo = (qst) => {
    $('#qstTitle').text(qst.title)
    $('#participantCount').text(qst.answersList.length)

    let problems = qst.problems;
    let answers = qst.answersList;
    for (let i = 0; i < problems.length; i++) {
        if(problems[i].type === 'single' || problems[i].type === 'multiple'){
            $('#questionnaire-statistics').append(`<div class="info">问题 ${i + 1}: ${problems[i].problemName}</div>`);

            // create a table for each question
            let tableHtml = `<table class="table table-striped"><thead><tr><th>选项</th><th>计数</th><th>百分比</th></tr></thead><tbody>`;
            let counts = [];
            let labels = [];

            for (let j = 0; j < problems[i].option.length; j++) {
                let count = 0;
                answers.map((ans) => {
                    if (ans.answers[i].includes(j)) count++;
                })
                counts.push(count);
                labels.push(problems[i].option[j].chooseTerm);
                tableHtml += `<tr><td>${problems[i].option[j].chooseTerm}</td><td>${count}</td><td>${((count / qst.answersList.length) * 100).toFixed(2)}%</td></tr>`;
            }

            // end the table
            tableHtml += `</tbody></table>`;

            $('#questionnaire-statistics').append(tableHtml);

            // Create a canvas for the chart
            $('#questionnaire-statistics').append(`<canvas id="chart${i}"></canvas>`);

            // Create the chart
            let ctx = document.getElementById(`chart${i}`).getContext('2d');
            if (problems[i].type === 'single') {
                // Create a pie chart for single-choice questions
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                            data: counts,
                            backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#cc65fe', '#ff6c8b']
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false
                    }
                });
            } else if (problems[i].type === 'multiple') {
                // Create a bar chart for multiple-choice questions
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '# of Votes',
                            data: counts,
                            backgroundColor: 'rgba(75,192,192,0.4)',
                            borderColor: 'rgba(75,192,192,1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        },
                        responsive: true,
                        maintainAspectRatio: false
                    }
                });
            }

            $('#questionnaire-statistics').append(`<hr>`);
        }
    }
}
