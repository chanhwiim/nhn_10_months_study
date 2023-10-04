package com.example.Employee;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class EmployeeGenerator implements Iterator<Emplyee> {

    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    private static final EmployeeGenerator INSTANCE = new EmployeeGenerator();

    public static EmployeeGenerator getEmployeeGenerator() {
        return INSTANCE;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Emplyee next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return new Emplyee(ID_GENERATOR.incrementAndGet(), "직원 " + ID_GENERATOR.get());
    }

}
