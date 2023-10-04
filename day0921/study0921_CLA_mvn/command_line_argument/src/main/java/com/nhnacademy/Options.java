package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class Options {
    public static void main(String[] args) {
        boolean flag = false;
        String version = "1.0.0";
        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();

        options.addOption("f", "flag", false, "상태를 출력한다.");
        options.addOption("v", "version", false, "paint the version");

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption("v")) {
                System.out.println("Version : " + version);
                System.exit(0);
            } else if (commandLine.hasOption("f")) {
                flag = true;
            }
        } catch (ParseException e) {
            System.err.println("옵션이 잘못되었습니다. ");
        }

    }
}
