const axios = require('axios'),
  chalk = require('chalk'),
  cliHtml = require('cli-html'),
  he = require('he');

const { printMsg, isCorrectQuestion } = require('./utils');
const basicURL = 'https://www.reddit.com/r/dailyprogrammer/search.json';
const getQuestion = async ({ question, difficulty }) => {
  const searchString = `title:Challenge #${question} ${difficulty}`;
  try {
    const response = await axios.get(basicURL, {
      params: {
        q: searchString,
        restrict_sr: 1,
      },
    });
    if (
      response != null &&
      response.data != null &&
      response.data.data != null
    ) {
      const data = response.data.data;
      if (
        data.children != null &&
        data.children.length > 0 &&
        isCorrectQuestion(data.children[0].data.title, question, difficulty)
      ) {
        const info = data.children[0].data;
        console.log(chalk.cyan.bold(info.title) + '\n');
        const decodedHtml = he.decode(info.selftext_html);  
        console.log(cliHtml(decodedHtml));
      } else {
        printMsg(
          `Question #${question} of difficulty '${difficulty}' Not Present.`
        );
      }
    } else {
      printMsg(
        `Question #${question} of difficulty '${difficulty}' Not Present`
      );
    }
  } catch (e) {
    printMsg('Some error occured', e);
  }
};

module.exports = {
  getQuestion
};
