package com.example.thread;

import com.example.Employee.EmployeeGenerator;
import com.example.Employee.Emplyee;

public class WorkerPool {

    private final Worker[] workers;

    public WorkerPool(int poolSize, Channel channel) {
        workers = new Worker[poolSize];
        for (int i = 0; i < poolSize; i++) {
            Emplyee emplyee = EmployeeGenerator.getEmployeeGenerator().next();
            workers[i] = new Worker(channel, emplyee);
        }
    }

    public void start() {
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
    }
}
