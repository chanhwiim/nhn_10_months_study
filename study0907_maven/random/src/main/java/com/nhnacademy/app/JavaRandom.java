package com.nhnacademy.app;

import java.util.Random;

public class JavaRandom {
    public static void main(String[] args) {

        Random random = new Random(1);

        for (int i = 0; i < 10; i++) {
            int randomValue = random.nextInt(100) + 1;
            System.out.println("java random: " + randomValue);
        }
    }

}
