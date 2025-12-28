package com.example.fruitables.payloads.results.success;

import com.example.fruitables.payloads.results.Result;

public class SuccessResult extends Result {
    public SuccessResult(boolean success) {
        super(true);
    }

    public SuccessResult(boolean success, String message) {
        super(true, message);
    }
}
