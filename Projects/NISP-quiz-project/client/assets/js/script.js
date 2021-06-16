'use strict';
// #############################################
// #############################################
// #############################################
// Global components and variables

const categories = document.querySelector('.categories');
const question = document.querySelector('.question');
const results = document.querySelector('.results');
const heading = document.querySelector('.heading');
const header = document.querySelector('.logo');
const answers = document.querySelector('.question__answers');
const questionCategory = document.querySelector('.question__category');
const questionQuestion = document.querySelector('.question__question');
const answersABCD = document.querySelectorAll('.question__answer');
const points = document.querySelector('.total-points');
const currentQuesionNumber = document.querySelector('.question__number');

const nextQuestionBtn = document.querySelector('.next-queston');
const playAgainBtn = document.querySelector('.results__restart');
const getResultsBtn = document.querySelector('.results__getPDF');

let questionNumber = 1;
let lastQuestion = false;

// #############################################
// #############################################
// #############################################
// Game functionality

// #############################################
// CATEGORY PICK
categories.addEventListener('click', e => {
  e.preventDefault();

  // Check if clicks occurs in one of the categories - if yes switch UI
  if (e.target.classList.contains('categories__category')) {
    categories.classList.toggle('hide');
    heading.classList.toggle('hide');
    question.classList.toggle('hide');
    header.classList.add('move-left');

    // Reading category
    const categoryName = e.target.dataset.category;
    questionCategory.textContent = `category: ${categoryName}`;

    // Send category to server
    sendCategory(categoryName);
  }
});

// #############################################
// Select answer
answers.addEventListener('click', e => {
  // Toggle activ class if clicks occurs in answer box
  if (e.target.classList.contains('question__answer')) {
    e.target.classList.toggle('active');
  }
});

// #############################################
// NEXT QUESTION BUTTON
nextQuestionBtn.addEventListener('click', () => {
  // Increase current question number
  questionNumber++;

  // Display current question
  currentQuesionNumber.textContent = `${questionNumber}/10`;

  // Create array of selected answers
  const selectedAnswers = document.querySelectorAll('.active');
  const answers = [];
  selectedAnswers.forEach(answer => answers.push(answer.dataset.answer));

  // Send selected answers to server
  sendAnswers(answers);

  // Remove active class from previously selected questions
  document.querySelectorAll('.active').forEach(el => el.classList.remove('active'));

  if (lastQuestion) {
    // switch UI if it was the last question
    question.classList.toggle('hide');
    results.classList.toggle('hide');
    header.classList.remove('move-left');
    return;
  }

  // Get next question
  getQuestion(questionNumber);
});

// #############################################
// GET RESULTS BUTTON

getResultsBtn.addEventListener('click', () => {
  getResults();
});

// #############################################
// PLAY AGAIN BUTTON
playAgainBtn.addEventListener('click', () => {
  results.classList.toggle('hide');
  categories.classList.toggle('hide');
  heading.classList.toggle('hide');
  header.classList.remove('move-left');
  nextQuestionBtn.textContent = 'Next question';
  document.querySelectorAll('.active').forEach(el => el.classList.remove('active'));
  lastQuestion = false;
  questionNumber = 1;
  currentQuesionNumber.textContent = `${questionNumber}/10`;
});

// #############################################
// #############################################
// #############################################
// Server communication

const COMMON_HEADER = 'http://127.0.0.1:8080/quiz/';

const sendCategory = async category => {
  await fetch(COMMON_HEADER + 'category', {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },

    body: JSON.stringify({ category: category }),
  })
    .then(
      () => {
        console.log('Category response succeed');

        // Get first question
        getQuestion(questionNumber);
      },
      () => console.log('Category response failed')
    )
    .catch(err => alert(err));
};

const getQuestion = async number => {
  await fetch(COMMON_HEADER + `question/${number}`)
    .then(response => response.json())
    .then(
      data => {
        console.log('GET question response succeed');

        questionQuestion.textContent = data.question;

        answersABCD.forEach((answer, index) => {
          answer.textContent = data.answers[index];
        });

        points.textContent = `Points for that question: ${data.points}`;

        lastQuestion = data.lastQuestion;

        if (lastQuestion) {
          nextQuestionBtn.textContent = 'Finish quiz';
        }
      },
      () => console.log('GET question response failed')
    )
    .catch(err => alert(err));
};

const sendAnswers = async answers => {
  await fetch(COMMON_HEADER + 'calculate', {
    method: 'put',
    headers: {
      'Content-type': 'application/json',
    },
    body: JSON.stringify({ questionId: questionNumber - 1, selectedAnswers: answers, lastQuestion: lastQuestion }),
  })
    .then(
      () => console.log('PUT answers response succeed'),
      () => console.log('PUT answers response failed')
    )
    .catch(err => alert(err));
};

const getResults = async () => {
  await fetch(COMMON_HEADER + 'report', {
    method: 'post',
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
    },
  })
    .then(
      () => console.log('PDF response succeed'),
      () => console.log('PDF response failed')
    )
    .catch(err => alert(err));
};
