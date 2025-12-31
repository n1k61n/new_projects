package com.example.fruitables.payloads.results.error;

import com.example.fruitables.payloads.results.Result;

public class ErrorResult extends Result {
    public ErrorResult(boolean error) {
        super(false);
    }

    public ErrorResult(boolean error, String message) {
        super(false, message);
    }
}
