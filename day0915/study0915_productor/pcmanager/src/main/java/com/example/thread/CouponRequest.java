package com.example.thread;

import java.io.InterruptedIOException;

import com.example.Coupon.CouponGenerator;
import com.example.nhnMart.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CouponRequest extends Request {

    private final Customer customer;
    private final ShoppingChannel shoppingChannel;

    public CouponRequest(Customer customer, ShoppingChannel shoppingChannel) {
        this.customer = customer;
        this.shoppingChannel = shoppingChannel;
    }

    @Override
    public void execute() {
        // 쿠폰 발급 로직.
        if (CouponGenerator.getCouponGenerator().hasNext()) {
            customer.addCoupon(CouponGenerator.getCouponGenerator().next());
            log.info("thread id : {}, customer-id : {} , coupond-id {}", Thread.currentThread().getId(),
                    customer.getId(), customer.getCouponList().get(0).getId());
        } else {
            log.info("thread id : {}, customer-id : {}, coupon id : {}", Thread.currentThread().getId(),
                    customer.getId(), "empty");
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("sleep", e);
        }
        shoppingChannel.addRequest(new ShoppingRequest(customer));
    }

}
