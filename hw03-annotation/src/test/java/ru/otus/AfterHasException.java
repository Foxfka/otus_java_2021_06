package ru.otus;

import ru.otus.annotation.Test;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;

public class AfterHasException {
    @Before
    public void before() {
        System.out.println("Before method was started");
    }

    @Test
    public void firstTest() {
        System.out.println("First test was started");
    }

    @Test
    public void secondTest() {
        System.out.println("Second test was started");
    }

    @Test
    public void thirdTest() {
        System.out.println("Third test was started");
    }

    @After
    public void after() {
        System.out.println("After method was started");
        throw new RuntimeException("Something went wrong in after method");
    }
}
