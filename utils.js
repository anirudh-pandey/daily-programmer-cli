const chalk = require('chalk');
const isNumeric = (str) => {
  if (typeof str != 'string') return false; // we only process strings!
  return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
    !isNaN(parseFloat(str)); // ...and ensure strings of whitespace fail
};

const isNaturalNumber = (val) => {
  return isNumeric(val) && Number(val) % 1 == 0 && Number(val) > 0; // to check if decimal or not.
};

const isCorrectQuestion = (title, question, difficulty) => {
  let qFound = false, dFound = false;
  for(let i=0; i<title.length; i++) {
    if(!qFound && title[i] === '#' && i +question.length < title.length) {
      const qInTitle = title.substr(i+1, question.length);
      if(question === qInTitle) 
        qFound = true;
    } else if(!dFound && title[i] === '[' && i +difficulty.length < title.length) {
      const dInTitle = title.substr(i +1, difficulty.length).toLowerCase();
      if(difficulty === dInTitle) 
        dFound = true;
    }
  }
  return qFound && dFound;
};

const printMsg = msg => {
  console.log(chalk.red(msg));
};

module.exports = { 
  isNaturalNumber, printMsg, isCorrectQuestion
};