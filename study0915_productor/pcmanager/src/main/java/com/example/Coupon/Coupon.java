package com.example.Coupon;

public class Coupon {
    private final long id;
    private final int amount;
    private final CouponType coupontype;

    private Coupon(long id, int amount, CouponType couponType) {
        this.id = id;
        this.amount = amount;
        this.coupontype = couponType;
    }

    public static Coupon ofDiscount(long id, int amount) {
        return new Coupon(id, amount, CouponType.DISCOUNT);
    }

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public CouponType getCouponType() {
        return coupontype;
    }

    enum CouponType {
        DISCOUNT,
        amount,
        PERFECT;
    }
}
