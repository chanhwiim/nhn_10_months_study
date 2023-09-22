package com.example.thread;

import java.util.concurrent.ThreadLocalRandom;

import com.example.nhnMart.Basket;
import com.example.nhnMart.Customer;
import com.example.nhnMart.Food;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingRequest extends Request {

    private final Customer customer;

    public ShoppingRequest(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public void execute() {
        Basket.addFood(new Food("오이", ThreadLocalRandom.current().nextInt(100, 900)));
        Basket.addFood(new Food("당근", ThreadLocalRandom.current().nextInt(100, 900)));
        Basket.addFood(new Food("사과", ThreadLocalRandom.current().nextInt(100, 900)));

        log.info("customer : {}, Basket : {} ", customer.getId(), Basket.info());

        Basket.reset();
    }

}
