package com.nhnacademy;

public class Quiz01 {
    public static void main(String[] args) {

        int i = 0;

        while (i < args.length) {
            if (args[i].equals("-ac")) {
                System.out.println("Option : " + args[i]);
                i++;
            } else if (args[i].equals("--path")) {
                System.out.println("Option : " + args[i]);
                i++;
            } else if (args[i].equals("-t")) {
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
