package vao.wallet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vao.wallet.server.RetrofitLoginData;
import vao.wallet.server.RetrofitLoginService;

public class LoginSubActivity extends AppCompatActivity {
    TextView textView3;
    EditText editTextId, editTextPassword;
    TextView idErrorMsg, passwordErrorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sub);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorStatusBar1));
        window.setNavigationBarColor(getResources().getColor(R.color.colorNavbarDefault));

        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setIncludeFontPadding(false);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        idErrorMsg = (TextView) findViewById(R.id.idErrorMsg);
        passwordErrorMsg = (TextView) findViewById(R.id.passwordErrorMsg);

        editTextId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextId.setBackgroundResource(R.drawable.vao_edittext_blue);
                } else {
                    editTextId.setBackgroundResource(R.drawable.vao_edittext);
                }
            }
        });

        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextPassword.setBackgroundResource(R.drawable.vao_edittext_blue);
                } else {
                    editTextPassword.setBackgroundResource(R.drawable.vao_edittext);
                }
            }
        });
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

    public void onclickLoginSubExit(View view) {
        Intent intent = new Intent(LoginSubActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void onclickLoginSub(View view) {
        boolean isFine = true;

        if (editTextId.getText().toString().length() > 4) {
            editTextId.setBackgroundResource(R.drawable.vao_edittext);
            idErrorMsg.setVisibility(View.INVISIBLE);
        } else {
            editTextId.setBackgroundResource(R.drawable.vao_edittext_red);
            idErrorMsg.setVisibility(View.VISIBLE);
            isFine = false;
        }

        if (editTextPassword.getText().toString().length() > 4) {
            editTextPassword.setBackgroundResource(R.drawable.vao_edittext);
            passwordErrorMsg.setVisibility(View.INVISIBLE);
        } else {
            editTextPassword.setBackgroundResource(R.drawable.vao_edittext_red);
            passwordErrorMsg.setVisibility(View.VISIBLE);
            isFine = false;
        }

        if (isFine) {
            startActivityForResult(new Intent(getApplicationContext(), DimLoadingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), 1003);

            Log.e("test", "aa");
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitLoginService.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitLoginService retrofitLoginService = retrofit.create(RetrofitLoginService.class);

            Map<String, String> loginData = new HashMap<>();
            loginData.put("username", editTextId.getText().toString());
            loginData.put("password", editTextPassword.getText().toString());

            retrofitLoginService.authLogin(loginData).enqueue(new Callback<RetrofitLoginData>() {
                @Override
                public void onResponse(Call<RetrofitLoginData> call, Response<RetrofitLoginData> response) {
                    if (response.isSuccessful()) {
                        RetrofitLoginData body = response.body();
                        if (body != null) {
                            Toast.makeText(getApplicationContext(), body.getMessage(), Toast.LENGTH_SHORT).show();

                            if (body.getSuccess()) {
                                finishActivity(1003);
                                Intent intent =  new Intent(LoginSubActivity.this, MainSubActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("token", body.getToken());
                                startActivity(intent);
                                finish();
                            } else {
                                finishActivity(1003);
                                editTextPassword.setBackgroundResource(R.drawable.vao_edittext_red);
                                passwordErrorMsg.setVisibility(View.VISIBLE);
                                editTextId.setBackgroundResource(R.drawable.vao_edittext_red);
                                idErrorMsg.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<RetrofitLoginData> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void onclickMakeWallet(View view) {
        Intent intent = new Intent(LoginSubActivity.this, JoinActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
