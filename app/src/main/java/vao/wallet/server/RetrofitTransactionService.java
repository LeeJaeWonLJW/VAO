package vao.wallet.server;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitTransactionService {
    String URL = "http://vao.fafrika.com:3000";

    @FormUrlEncoded
    @POST("/transaction/send")
    Call<RetrofitTransactionData> transactionSend(
            @FieldMap Map<String, String> options
    );
}
