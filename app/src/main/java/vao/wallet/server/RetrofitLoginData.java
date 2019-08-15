package vao.wallet.server;

public class RetrofitLoginData {
    private final Boolean success;
    private final String code;
    private final int status;
    private final String message;
    private final String token;

    public RetrofitLoginData(boolean success, String code, int status, String message, String token) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.token = token;
        this.status = status;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }
}
