package com.nhnacademy.app;

import org.apache.commons.math3.random.RandomDataGenerator;

public class ApachCommonsRandom {
    public static void main(String[] args) {
        RandomDataGenerator random = new RandomDataGenerator();
        int randomValue = random.nextInt(0, 100);
        System.out.println(randomValue);
    }
}
