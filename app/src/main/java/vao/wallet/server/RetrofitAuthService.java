package vao.wallet.server;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vao.wallet.server.RetrofitAuthData;

public interface RetrofitAuthService {
    String URL = "http://vao.fafrika.com:3000";

    @FormUrlEncoded
    @POST("/auth/register")
    Call<RetrofitAuthData> authRegister(
            @FieldMap Map<String, String> options
    );

    @FormUrlEncoded
    @POST("/auth/check")
    Call<RetrofitAuthData> authCheck(
            @FieldMap Map<String, String> options
    );
}
