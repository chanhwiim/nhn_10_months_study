package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

public class IOptionsAddHelp {
    public static void main(String[] args) {
        boolean flag = false;
        String version = "1.0.0";
        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();

        options.addOption("f", "flag", false, "상태를 출력한다.");
        options.addOption("v", "version", false, "paint the version");
        options.addOption("i", "id", true, "식별자");
        options.addOption("h", "help", false, "도움말");
        Option idOption = Option.builder("i").hasArg().argName("id").desc("식별자입니다.").build();

        options.addOption(idOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args);
            HelpFormatter helpFormatter = new HelpFormatter();
            if (commandLine.hasOption("h")) {
                helpFormatter.printHelp("test", options);
            }
            if (commandLine.hasOption("v")) {
                System.out.println("Version : " + version);
                System.exit(0);
            }
            if (commandLine.hasOption("f")) {
                flag = true; // 예시.
            }
            if (commandLine.hasOption("i")) {
                System.out.println("ID: " + commandLine.getOptionValue("i"));
            }

            // System.out.println(commandLine.getArgList().toString());
        } catch (ParseException e) {
            System.err.println("옵션이 잘못되었습니다. ");
            // HelpFormatter helpFormatter = new HelpFormatter();
            // helpFormatter.printHelp("test", options);
        }

    }
}
