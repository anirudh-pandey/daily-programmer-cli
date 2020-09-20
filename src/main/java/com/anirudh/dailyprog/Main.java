package com.anirudh.dailyprog;

import picocli.CommandLine;


public class Main {

    public static void main(String[] args) {
        // write your code here
        int exitCode = new CommandLine(new DailyProg()).setCaseInsensitiveEnumValuesAllowed(true).execute(args);
        System.exit(exitCode);
    }
}
//description = "Get questions from r/dailyprogrammer subreddit",