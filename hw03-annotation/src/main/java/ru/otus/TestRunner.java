package ru.otus;

import ru.otus.annotation.Test;
import ru.otus.annotation.After;
import ru.otus.annotation.Before;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class TestRunner {
    public static TestCounter run(String className) {
        TestCounter testCounter = null;
        try {
            Class<?> aClass = Class.forName(className);

            testCounter = runTests(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (testCounter == null) {
                testCounter = new TestCounter(0, 0, 0);
            }

            System.out.println("Count of running tests: " + testCounter.getRunTestCount());
            System.out.println("Count of success tests: " + testCounter.getSuccessTestCount());
            System.out.println("Count of failed tests: " + testCounter.getFailedTestCount());
        }

        return testCounter;
    }

    public static TestCounter runTests(Class<?> aClass) {
        AtomicInteger runTestCount = new AtomicInteger(0);
        AtomicInteger successTestCount = new AtomicInteger(0);
        AtomicInteger failedTestCount = new AtomicInteger(0);

        Arrays.stream(aClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Test.class))
                .forEach(method -> {
                    try {
                        runTestCount.incrementAndGet();
                        Object instance = ReflectionHelper.instantiate(aClass);

                        callAnnotatedMethods(instance, Before.class);
                        ReflectionHelper.callMethod(instance, method.getName());
                        callAnnotatedMethods(instance, After.class);

                        successTestCount.incrementAndGet();
                    } catch (Exception e) {
                        failedTestCount.incrementAndGet();
                    }
                });
        return new TestCounter(runTestCount.get(), successTestCount.get(), failedTestCount.get());
    }

    private static void callAnnotatedMethods(Object instance, Class<? extends Annotation> annotation) {
        Arrays.stream(instance.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .forEach(method -> ReflectionHelper.callMethod(instance, method.getName()));
    }
}
