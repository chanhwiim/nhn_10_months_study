package com.example.thread;

import java.util.LinkedList;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingChannel {

    private final Queue<Request> queue;
    private final int MAX_QUEUE_SIZE = 10;

    public ShoppingChannel() {
        queue = new LinkedList<>();
    }

    public synchronized void addRequest(Request request) {
        while (queue.size() >= MAX_QUEUE_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("add request wait", e);
            }
        }
        queue.add(request);
        notifyAll();
    }

    public synchronized Request getRequest() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Request request = queue.poll();
        notifyAll();
        return request;
    }

}
