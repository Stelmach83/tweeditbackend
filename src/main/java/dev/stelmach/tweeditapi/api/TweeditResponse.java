package dev.stelmach.tweeditapi.api;

public class TweeditResponse {

    private int code;
    private String status;
    private Object message;

    public TweeditResponse() {
    }

    public TweeditResponse(int code, String status, Object message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
