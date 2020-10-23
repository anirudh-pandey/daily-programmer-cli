#! /usr/bin/env node
const yargs = require('yargs');
const { isNaturalNumber, printMsg } = require('./utils.js'),
      { getQuestion } = require('./api');

yargs.version('1.2.2');

yargs.options({
  q: {
    alias: 'question',
    demandOption: true,
    describe: 'Enter the question number',
    type: 'string',
  },
  d: {
    alias: 'difficulty',
    demandOption: true,
    describe: 'Choose a difficulty level',
    choices: ['easy', 'intermediate', 'hard'],
    type: 'string',
  },
});

yargs.example([['$0 -q 120 -d easy'], ['$0 --question 120 --difficulty easy']]);

yargs.parse();

if (yargs.argv.q != null && yargs.argv.d != null) {
  if (isNaturalNumber(yargs.argv.q)) {
    getQuestion(yargs.argv);
  } else {
    printMsg('Pls enter a valid question number.');
  }
}
