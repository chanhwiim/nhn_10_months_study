package com.nhnacademy;

public class Exam02 {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                System.out.println("Float : " + Float.parseFloat(arg));
            } catch (NumberFormatException ignore) {
                System.out.println("String : " + arg);
            }
        }
    }
}