onload = () => {
  const problems = JSON.parse(localStorage.getItem('problems'));
  problems.forEach((problem, index) => {
    let problemHTML = '';
    switch (problem.type) {
      case 'single':
        problemHTML = generateSingleChoiceHTML(problem, index);
        break;
      case 'multiple':
        problemHTML = generateMultipleChoiceHTML(problem, index);
        break;
      case 'fib':
        problemHTML = generateTextHTML(problem, index);
        break;
      case 'matrix':
        problemHTML = generateMatrixHTML(problem, index);
        break;
      case 'gauge':
        problemHTML = generateScaleHTML(problem, index);
        break;
      default:
        console.error(`Unsupported problem type: ${problem.type}`);
    }
    $('#problem').append(problemHTML);
  });
}

function generateSingleChoiceHTML(problem, index) {
  return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        ${problem.option.map((option) => `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm${index}">${option.chooseTerm}
            </label>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function generateMultipleChoiceHTML(problem, index) {
  return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        ${problem.option.map((choice) => `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm${index}">${choice.chooseTerm}
            </label>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function generateTextHTML(problem, index) {
  return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
      </div>
    </div>
  `;
}

function generateMatrixHTML(problem, index) {
  return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom">
        <table class="table">
          <thead>
            <tr>
              <th></th>
              ${problem.option.map(choice => `<th>${choice.chooseTerm}</th>`).join('')}
            </tr>
          </thead>
          <tbody>
            ${problem.leftTitle.split(',').map((row, rIndex) => `
              <tr>
                <td>${row}</td>
                ${problem.option.map((choice, cIndex) => `
                  <td><input type="radio" name="chooseTerm${index}${rIndex}" /></td>
                `).join('')}
              </tr>
            `).join('')}
          </tbody>
        </table>
      </div>
    </div>
  `;
}

function generateScaleHTML(problem, index) {
  return `
    <div class="question" id="question${index}" data-type="${problem.type}" data-problemIndex="${index}">
      <div class="top">
        <span class="question-title" id="questionTitle">${index + 1}.${problem.problemName}</span>
        <span class="must-answer" id="mustAnswer">${problem.mustAnswer ? '必答题' : ''}</span>
      </div>
      <div class="bottom" style="display: flex; align-items: center; justify-content: space-between;">
        <div>${problem.option[0].chooseTerm}</div>
        ${problem.option.map((option, sIndex) => `
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction${index}" />${option.fraction}
            </label>
          </div>
        `).join('')}
        <div>${problem.option[problem.option.length - 1].chooseTerm}</div>
      </div>
    </div>
  `;
}
