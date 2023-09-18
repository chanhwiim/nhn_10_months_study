package com.example.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Channel {
    private final Queue<Request> queue;
    private final int queueMaxSize;

    public Channel(int queueMaxSize) {
        this.queueMaxSize = queueMaxSize;
        queue = new LinkedList<>();
        // queue = new LinkedBlockingQueue<>(queueSize);
    }

    public synchronized Request takeRequest() {
        return queue.poll();
    }

    public synchronized void addRequest(Request request) {
        while (queue.size() >= queueMaxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("add queue ", e);
            }
        }
        queue.add(request);
        notifyAll();
    }
}
