package com.udacity.gradle.builditbigger;

public interface AsyncResponseHandler {
    void goodResult(String output);
    void badResult(Exception exception);
}
