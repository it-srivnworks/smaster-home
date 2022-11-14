package com.srivn.works.smaster.smasterhome.utils;

import java.io.IOException;

public class RetryLogic {

    int retryAttempts;
    final long TIME_TO_WAIT;

    RetryLogic(int retryAttempts, long timeToWait) {
        this.retryAttempts = retryAttempts;
        this.TIME_TO_WAIT = timeToWait;
    }

    public void retryImpl(RetryImplementation retryImplementation) throws IOException {
        if (shouldRetry()) {
            retryAttempts--;
            retryImplementation.run();
            waitBeforeNextRetry();
        } else {

            throw new IOException();
        }
    }


    public boolean shouldRetry() {
        return retryAttempts > 0;
    }

    public void waitBeforeNextRetry() {
        try {
            Thread.sleep(TIME_TO_WAIT);
        } catch (Exception e) {
        }
    }
}
