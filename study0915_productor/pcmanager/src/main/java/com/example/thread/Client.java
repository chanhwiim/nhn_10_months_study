package com.example.thread;

import com.example.nhnMart.Customer;
import com.example.nhnMart.CustomerGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client extends Thread {
    private final Channel channel;
    private final ShoppingChannel shoppingChannel;

    public Client(Channel channel, ShoppingChannel shoppingChannel) {
        this.channel = channel;
        this.shoppingChannel = shoppingChannel;
    }

    @Override
    public void run() {
        while (true) {
            Customer customer = CustomerGenerator.gCustomerGenerator().next();
            // Request request = new CouponRequest(customer);
            Request request = new CouponRequest(CustomerGenerator.gCustomerGenerator().next(), shoppingChannel);

            channel.addRequest(request);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }

}
