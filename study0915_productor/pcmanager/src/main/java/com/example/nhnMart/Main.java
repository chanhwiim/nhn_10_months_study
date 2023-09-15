package com.example.nhnMart;

import com.example.Coupon.Coupon;

public class Main {
    public static void main(String[] args) {
        Customer customer = Customer.of(1, "macro");

        Coupon coupon = Coupon.ofDiscount(1, 10000);
    }
}
