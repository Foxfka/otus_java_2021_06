package ru.otus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRunnerTest {

    @Test
    void runSuccessTests() {
        TestCounter run = TestRunner.run("ru.otus.SuccessTest");

        assertEquals(3, run.getRunTestCount());
        assertEquals(3, run.getSuccessTestCount());
        assertEquals(0, run.getFailedTestCount());
    }

    @Test
    void runTestHasException() {
        TestCounter run = TestRunner.run("ru.otus.TestHasException");

        assertEquals(3, run.getRunTestCount());
        assertEquals(2, run.getSuccessTestCount());
        assertEquals(1, run.getFailedTestCount());
    }

    @Test
    void runBeforeHasException() {
        TestCounter run = TestRunner.run("ru.otus.BeforeHasException");

        assertEquals(3, run.getRunTestCount());
        assertEquals(0, run.getSuccessTestCount());
        assertEquals(3, run.getFailedTestCount());
    }

    void runAfterHasException() {
        TestCounter run = TestRunner.run("ru.otus.AfterHasException");

        assertEquals(3, run.getRunTestCount());
        assertEquals(0, run.getSuccessTestCount());
        assertEquals(3, run.getFailedTestCount());
    }
}
