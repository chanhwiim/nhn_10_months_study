package com.example.Coupon;

import com.example.Enum.CouponType;
// import java.util.concurrent.*;

public class CouponMacro {
    private final long id;
    private final int amount;
    private final CouponType coupontype;

    // private final LinkedBlockingQueue<Coupon> linkedBlockingQueue;

    private CouponMacro(long id, int amount, CouponType couponType) {
        // linkedBlockingQueue = new LinkedBlockingQueue<>(50);
        this.id = id;
        this.amount = amount;
        this.coupontype = couponType;
    }

    public static CouponMacro ofDiscount(long id, int amount) {
        return new CouponMacro(id, amount, CouponType.DISCOUNT);
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
}
