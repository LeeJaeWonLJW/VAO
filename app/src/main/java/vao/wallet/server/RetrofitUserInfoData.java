package vao.wallet.server;

import com.google.gson.JsonObject;

public class RetrofitUserInfoData {
    private Boolean success;
    private final JsonObject message;
    private final String code;

    public RetrofitUserInfoData(boolean success, JsonObject message, String code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public JsonObject getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
