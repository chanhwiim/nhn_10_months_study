package com.example.thread;

import javax.management.RuntimeErrorException;

public class ShoppingWorker implements Runnable {

    private final ShoppingChannel shoppingChannel;

    public ShoppingWorker(ShoppingChannel shoppingChannel) {
        this.shoppingChannel = shoppingChannel;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            if (i == Integer.MIN_VALUE) {
                break;
            }

            Request request = null;

            try {
                request = shoppingChannel.getRequest();
                request.execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
        }

    }

}
