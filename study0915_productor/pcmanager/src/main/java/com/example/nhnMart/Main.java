package com.example.nhnMart;

import java.util.concurrent.*;

import com.example.Coupon.Coupon;
import com.example.Employee.Emplyee;
import com.example.thread.Channel;
import com.example.thread.Client;
import com.example.thread.ShoppingChannel;
import com.example.thread.ShoppingWorker;
import com.example.thread.WorkerPool;
import com.example.threadLocal.SharedUserContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Customer customer = Customer.of(1, "macro");

        // Coupon coupon = Coupon.ofDiscount(1, 10000);

        // ExecutorService executor = Executors.newFixedThreadPool(3);
        // Emplyee emplyee1 = new Emplyee(1, "Bystard1");
        // Emplyee emplyee2 = new Emplyee(1, "Bystard2");
        // Emplyee emplyee3 = new Emplyee(1, "Bystard3");

        // executor.execute(emplyee1);
        // Thread.sleep(200);
        // executor.execute(emplyee2);
        // Thread.sleep(200);
        // executor.execute(emplyee3);
        // Thread.sleep(200);

        // executor.shutdown();
        // customer.start();

        // for (int i = 0; i < 10; i++) {
        // Customer customer2 = CustomerGenerator.gCustomerGenerator().next();
        // log.info("customer2 : {}", customer2);
        // }

        ShoppingChannel shoppingChannel = new ShoppingChannel();
        ExecutorService executorsService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorsService.execute(new ShoppingWorker(shoppingChannel));
        }

        Channel channel = new Channel(20);
        WorkerPool workerPool = new WorkerPool(3, channel);
        workerPool.start();
        log.info("finished");

        new Thread(new Client(channel, shoppingChannel)).start();
        new Thread(new Client(channel, shoppingChannel)).start();
        new Thread(new Client(channel, shoppingChannel)).start();

        // SharedUserContext user1 = new SharedUserContext(1);
        // SharedUserContext user2 = new SharedUserContext(2);

        // new Thread(user1).start();
        // new Thread(user2).start();

    }
}
