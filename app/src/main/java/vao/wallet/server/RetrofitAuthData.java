package vao.wallet.server;

public class RetrofitAuthData {
    private final Boolean success;
    private final String code;
    private final String message;
    private final String token;
    private final Boolean status;

    public RetrofitAuthData(boolean success, String code, String message, String token, Boolean status) {
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

    public String getToken() {
        return token;
    }

    public Boolean getStatus() {
        return status;
    }
}
