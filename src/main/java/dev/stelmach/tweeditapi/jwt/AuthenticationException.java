package dev.stelmach.tweeditapi.jwt;

class AuthenticationException extends RuntimeException {

    AuthenticationException(String message) {
        super(message);
    }

    AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

}