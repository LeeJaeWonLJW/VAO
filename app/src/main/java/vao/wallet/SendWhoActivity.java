package vao.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vao.wallet.server.RetrofitTransactionData;
import vao.wallet.server.RetrofitTransactionService;

public class SendWhoActivity extends AppCompatActivity {
    double cost;
    String myAddress;

    TextView sendWhoPageTitle;
    EditText sendWhoAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_who);

        sendWhoPageTitle = (TextView) findViewById(R.id.sendWhoPageTitle);
        sendWhoAddress = (EditText) findViewById(R.id.sendWhoAddress);

        Intent getData = getIntent();
        cost = Double.parseDouble(getData.getStringExtra("cost"));
        myAddress = getData.getStringExtra("myAddress");
        sendWhoPageTitle.setText(cost + " KP 송금하기");
    }

    @Override
    public void onBackPressed() {
        // Disable onBackPressed
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    public void onclickSendBoxCancel(View view) {
        finish();
    }

    public void onclickSendWhoPageBack(View view) {
        finish();
    }

    public void onclickSendBoxSend(View view) {
        startActivityForResult(new Intent(getApplicationContext(), DimLoadingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), 1003);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitTransactionService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitTransactionService retrofitTransactionService = retrofit.create(RetrofitTransactionService.class);

        Map<String, String> transactionData = new HashMap<>();
        transactionData.put("from", myAddress);
        transactionData.put("to", sendWhoAddress.getText().toString());
        transactionData.put("amount", Double.toString(cost));

        retrofitTransactionService.transactionSend(transactionData).enqueue(new Callback<RetrofitTransactionData>() {
            @Override
            public void onResponse(Call<RetrofitTransactionData> call, Response<RetrofitTransactionData> response) {
                if (response.isSuccessful()) {
                    RetrofitTransactionData body = response.body();
                    if (body != null) {
                        finishActivity(1003);
                        Toast.makeText(getApplicationContext(), body.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<RetrofitTransactionData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
