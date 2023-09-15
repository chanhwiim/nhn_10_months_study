package com.nhnacademy;

import ch.qos.logback.classic.Logger;

public abstract class Node {
    final String id;
    static Integer count;
    Logger logger;

    protected Node(String id) {
        this.id = id;
    }

    public String getName() {
        return this.id;
    }

    public Integer getCount() {
        return count;
    }
}
