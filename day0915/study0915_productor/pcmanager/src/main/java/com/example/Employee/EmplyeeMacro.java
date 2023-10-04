package com.example.Employee;

import java.util.concurrent.LinkedBlockingQueue;

import com.example.Coupon.Coupon;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmplyeeMacro {
    private final long id;
    private final String name;
    static LinkedBlockingQueue<Coupon> couponAccount = new LinkedBlockingQueue<Coupon>(50);

    public EmplyeeMacro(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // @Override
    // public synchronized void run() {
    // Coupon coupon = Coupon.ofDiscount(1, 10000);
    // while (couponAccount.size() != 50) {
    // couponAccount.add(coupon);
    // }
    // while (true) {
    // try {
    // if (!couponAccount.isEmpty()) {
    // couponAccount.remove();
    // Thread.sleep(1000);
    // log.info("현재 남은 쿠폰의 수는 : " + couponAccount.size() + " 입니다.");
    // } else {
    // Thread.sleep(1000);
    // log.info("쿠폰의 수량이 남아있지 않습니다.");
    // }
    // } catch (InterruptedException e) {
    // Thread.currentThread().interrupt();
    // }
    // }
    // }
}
