package com.example.ex01;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

@Slf4j
public class PCManager {
    private final LinkedList<Integer> buffer = new LinkedList<>();
    private final int bufferSize;

    public PCManager(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public void produce() throws InterruptedException {
        int data = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() == bufferSize) {
                    wait();
                }
                buffer.add(++data);
                log.info("생산자 - 데이터 생성 : {}", data);
                notifyAll();

                Thread.sleep(500);
            }
        }
        // synchronized (this) {
        // if (bufferSize > buffer.size()) {
        // buffer.addFirst(data++);
        // notifyAll();
        // } else
        // wait();
        // } // 내가 짠거.
    }

    public void consume() throws InterruptedException {

        while (true) {
            synchronized (this) {
                while (buffer.isEmpty()) {
                    wait();
                }
                int data = buffer.removeFirst();
                log.info("소비자 - 데이터 소비 : {}", data);
                notifyAll();

                Thread.sleep(500);
            }
        }
        // synchronized (this) {
        // if (!buffer.isEmpty()) {
        // buffer.removeFirst();
        // notifyAll();
        // } else {
        // wait();
        // }
        // } // 이하 동일
    }
}