package com.example.thread;

import com.example.Employee.Emplyee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Worker implements Runnable {

    private final Channel channel;

    private final Emplyee emplyee;

    public Worker(Channel channel, Emplyee emplyee) {
        this.channel = channel;
        this.emplyee = emplyee;
    }

    @Override
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            if (request != null) {
                request.execute();
                log.info("직원 : {}이 쿠폰을 발급하였습니다. ", emplyee.getName());
            }
        }

    }

}
