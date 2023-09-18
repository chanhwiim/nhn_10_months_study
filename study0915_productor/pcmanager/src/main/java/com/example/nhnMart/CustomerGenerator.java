package com.example.nhnMart;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class CustomerGenerator implements Iterator<Customer> {

    private static final CustomerGenerator INSTANCE = new CustomerGenerator();

    private static final AtomicLong ID_Generate = new AtomicLong();

    private CustomerGenerator() {
    }

    public static CustomerGenerator gCustomerGenerator() {
        return INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Customer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return Customer.of(ID_Generate.incrementAndGet(), "고객" + ID_Generate.get());
    }

    // public static Customer getCustomer(){
    // return Customer.of(ID_Generate.incrementAndGet(), "고객" + ID_Generate.get());
    // }

}
