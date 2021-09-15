package ru.otus;

public class TestCounter {
    public TestCounter(int runTestCount, int successTestCount, int failedTestCount) {
        this.runTestCount = runTestCount;
        this.successTestCount = successTestCount;
        this.failedTestCount = failedTestCount;
    }

    public int getRunTestCount() {
        return runTestCount;
    }

    public int getSuccessTestCount() {
        return successTestCount;
    }

    public int getFailedTestCount() {
        return failedTestCount;
    }

    private final int runTestCount;
    private final int successTestCount;
    private final int failedTestCount;

}
