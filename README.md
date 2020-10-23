
<h1 align="center">
  <br>
  <img src="images/dailyprog.png" alt="DailyProg" width="200">
  <br>
  DailyProg
  <br>
</h1>

<h4 align="center">Fetch coding questions from the <strong>r/dailyprogrammer</strong> subreddit easily from your terminal. No need to open your browser.</h4>

<p align="center">
  <a href="https://badge.fury.io/js/dailyprog">
    <img src="https://badge.fury.io/js/dailyprog.svg"
         alt="Gitter">
  </a>
</p>

<p align="center">
  <a href="#build-with">Build With</a> •
  <a href="#installation">Installation</a> •
  <a href="#usage">Usage</a> •
  <a href="#credits">Credits</a> •
  <a href="#support">Support</a> •
  <a href="#license">License</a>
</p>

![screenshot](https://raw.githubusercontent.com/amitmerchant1990/electron-markdownify/master/app/img/markdownify.gif)

## Build With

    Node

## Installation

With [npm](https://npmjs.org/) installed, run

    $ npm install -g dailyprog

## Usage

    $ dailyprog -q <questionNumber> -d <difficultyLevel>

### Options

```
Options:
      --help        Show help                                          [boolean]
      --version     Show version number                                [boolean]
  -q, --question    Enter the question number                [string] [required]
  -d, --difficulty  Choose a difficulty level                [string] [required] [choices: "easy", "intermediate", "hard"]
                   
```

### Examples

```
# Shorter Way
$ dailyprog -q 120 -d easy

# Longer Way
$ dailyprog --question 120 --difficulty easy
```

## Credits

This software uses the following open source packages:

- [Node.js](https://nodejs.org/)
- [yargs](https://www.npmjs.com/package/yargs)
- [axios](https://www.npmjs.com/package/axios)
- [chalk](https://www.npmjs.com/package/chalk)
- [cli-html](https://www.npmjs.com/package/cli-html)
- [he](https://www.npmjs.com/package/he)


## Support

If you found my project helpful, give it a :star:

<!-- ## You may also like...

- [Pomolectron](https://github.com/amitmerchant1990/pomolectron) - A pomodoro app
- [Correo](https://github.com/amitmerchant1990/correo) - A menubar/taskbar Gmail App for Windows and macOS -->

## License

ISC

---

<!-- > [amitmerchant.com](https://www.amitmerchant.com) &nbsp;&middot;&nbsp; -->
> GitHub [@SeverusTheBest](https://github.com/SeverusTheBest) &nbsp;&middot;&nbsp;

