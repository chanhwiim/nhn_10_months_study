package com.example.Coupon;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class CouponGenerator implements Iterator<Coupon> {
    private static final int COUPON_MAX_SIZE = 50;
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    private static final CouponGenerator INTANCE = new CouponGenerator();

    public static CouponGenerator getCouponGenerator() {
        return INTANCE;
    }

    @Override
    public synchronized boolean hasNext() {
        // return false;
        return ID_GENERATOR.get() < COUPON_MAX_SIZE;
    }

    @Override
    public Coupon next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return Coupon.ofDiscount(ID_GENERATOR.incrementAndGet(), 10_000);
    }
}
