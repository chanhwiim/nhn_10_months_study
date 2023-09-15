package com.example.ex01;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EX01Main {
    public static void main(String[] args) {
        final PCManager pc = new PCManager(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    log.error("InterruptedExcetion", e);

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    log.error("InterruptedExcetion", e);
                }
            }
        });

        t1.start();
        t2.start();
    }
}
