package com.example.nhnMart;

import java.util.ArrayList;
import java.util.List;

import com.example.Coupon.Coupon;

public class Customer implements Runnable {
    Thread thread;
    private final long id;
    private final String name;
    private int money;
    private final List<Coupon> couponList = new ArrayList<>();

    private Customer(long id, String name, int money) {
        thread = new Thread(this);
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public static Customer of(long id, String name) {
        return new Customer(id, name, 1000000);
    }

    public void addCoupon(Coupon coupon) {
        couponList.add(coupon);
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    @Override
    public void run() {

    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", money=" + money + ", couponList="
                + couponList + "]";
    }
}
