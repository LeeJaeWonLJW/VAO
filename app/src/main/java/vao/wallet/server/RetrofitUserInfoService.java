package vao.wallet.server;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetrofitUserInfoService {
    String URL = "http://vao.fafrika.com:3000";

    @POST("/user/info")
    Call<RetrofitUserInfoData> userInfo(
            @Header("x-access-token") String token
    );
}
