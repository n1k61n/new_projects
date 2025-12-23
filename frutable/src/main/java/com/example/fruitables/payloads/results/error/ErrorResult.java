package com.example.fruitables.payloads.results.error;

import com.example.fruitables.payloads.results.Result;

public class ErrorResult extends Result {
    public ErrorResult(boolean success) {
        super(success);
    }

    public ErrorResult(boolean success, String message) {
        super(success, message);
    }
}
