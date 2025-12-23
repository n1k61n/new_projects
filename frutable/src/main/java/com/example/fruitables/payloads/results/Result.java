package com.example.fruitables.payloads.results;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private boolean success;
    private String message;

    public Result(boolean success){
        this.success = success;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
