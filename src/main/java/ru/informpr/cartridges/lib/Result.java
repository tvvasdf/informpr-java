package ru.informpr.cartridges.lib;

public class Result {
    Boolean success;
    String message;

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public Result(Boolean success) {
        this.success = success;
    }
}
