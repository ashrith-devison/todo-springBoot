package edu.projects.ashrith.todo.exceptions;

public class ApiError extends RuntimeException {
    private String message;
    public ApiError(String message) {
        super(message);
        this.message = message;
    }

    public static ApiError UserAlreadyExists(String message) {
        throw new ApiError(message);
    }

    public static ApiError UserNotFound(String message) {
        throw new ApiError(message);
    }

    public static ApiError TaskNotFound(String message) {
        throw new ApiError(message);
    }
}
