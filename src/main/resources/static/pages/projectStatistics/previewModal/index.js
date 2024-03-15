onload = () => {
    console.log("Modal reloaded")
    const qstInfo = JSON.parse(sessionStorage.getItem('qstInfo'));
    const participantAnswers = JSON.parse(sessionStorage.getItem('participantAnswers'));

    console.log(qstInfo.problems);
    console.log(participantAnswers);

    $('.questionnaire-title').text(qstInfo.title);
    $('.questionnaire-description').text(qstInfo.description);

    qstInfo.problems.forEach((problem, index) => {
        let problemHTML = '';
        const answer = participantAnswers[index]; // Corresponding answer for this problem
        switch (problem.type) {
            case 'single':
                problemHTML = generateSingleChoiceHTML(problem, index, answer);
                break;
            case 'multiple':
                problemHTML = generateMultipleChoiceHTML(problem, index, answer);
                break;
            case 'fib':
                problemHTML = generateTextHTML(problem, index, answer);
                break;
            case 'matrix':
                problemHTML = generateMatrixHTML(problem, index, answer);//Not Viable
                break;
            case 'gauge':
                problemHTML = generateScaleHTML(problem, index, answer);//Not Viable
                break;
            default:
                console.error(`Unsupported problem type: ${problem.type}`);
        }
        $('#problem').append(problemHTML);
    });
}

function generateSingleChoiceHTML(problem, index, answer) {
    return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        ${problem.option.map((option, optionIndex) => `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm${index}" disabled ${answer.includes(optionIndex) ? 'checked' : ''}>${option.chooseTerm}
            </label>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function generateMultipleChoiceHTML(problem, index, answer) {
    return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        ${problem.option.map((option, optionIndex) => `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm${index}" disabled ${answer.includes(optionIndex) ? 'checked' : ''}>${option.chooseTerm}
            </label>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function generateTextHTML(problem, index, answer) {
    return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        <textarea style="width:100%" readonly>${answer}</textarea>
      </div>
    </div>
  `;
}

function generateMatrixHTML(problem, index, answer) {
    return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        ${problem.option.map((option, optionIndex) => `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label>${option.chooseTerm}: </label>
            <input type="text" readonly value="${answer[optionIndex]}">
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function generateScaleHTML(problem, index, answer) {
    return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
          <input type="range" min="0" max="100" step="1" readonly value="${answer}">
          <span>${answer}</span>
        </div>
      </div>
    </div>
  `;
}

