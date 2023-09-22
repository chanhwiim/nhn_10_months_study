package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

public class Quiz01Options {
    public static void main(String[] args) {
        boolean flag = false;
        int i = 0;
        org.apache.commons.cli.Options options = new org.apache.commons.cli.Options();

        options.addOption("a", "all", false, "설명서에 페이지에 일치하는 모든 항목 찾기.");
        options.addOption("v", "version", false, "버전");
        options.addOption("p", "path", true, "path");
        options.addOption("s", "size", true, "판의 크기");
        options.addOption("h", "host", true, "호스트");

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;

        while (i < args.length) {
            if (args[i].equals("-ac")) {
                System.out.println("Option : " + args[i]);
                i++;
            }
            if (args[i].equals("--path")) {
                System.out.println("Option : " + args[i]);
                i++;
            }
            if (args[i].equals("-t")) {
                if (i + 1 < args.length) {
                    System.out.println(args[i] + " " + args[i + 1]);
                } else {
                    System.out.println("-t의 옵션이 누락되었습니다.");
                    System.exit(1);
                }
                i += 2;
            } else {
                System.out.println("data : " + args[i]);
                i++;
            }
        }
        // for (int i = 0; i < args.length; i++){}

        // for (String arg : args) {
        // if (arg.equals("-t")) {
        // System.out.print(arg + " ");
        // } else {
        // System.out.println(arg);
        // }
        // }
    }
}
