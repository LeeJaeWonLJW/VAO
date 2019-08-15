package vao.wallet.server;

public class RetrofitTransactionData {
    private final Boolean success;
    private final String code;
    private final String message;
    private final String error;

    public RetrofitTransactionData(boolean success, String code, String message, String error) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.error = error;
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

    public String getError() {
        return error;
    }
}
