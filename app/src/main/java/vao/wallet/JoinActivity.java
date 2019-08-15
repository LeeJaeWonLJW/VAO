package vao.wallet;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vao.wallet.server.RetrofitAuthData;
import vao.wallet.server.RetrofitAuthService;

public class JoinActivity extends AppCompatActivity {

    ImageButton checkBox;
    EditText editTextId, editTextPassword, editTextPasswordConfirm;
    TextView idErrorMsg, passwordErrorMsg, passwordConfirmErrorMsg;

    boolean isCheckBoxChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorStatusBar1));
        window.setNavigationBarColor(getResources().getColor(R.color.colorNavbarDefault));

        checkBox = (ImageButton) findViewById(R.id.checkBox);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);

        idErrorMsg = (TextView) findViewById(R.id.idErrorMsg);
        passwordErrorMsg = (TextView) findViewById(R.id.passwordErrorMsg);
        passwordConfirmErrorMsg = (TextView) findViewById(R.id.passwordConfirmErrorMsg);

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

        editTextPasswordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextPasswordConfirm.setBackgroundResource(R.drawable.vao_edittext_blue);
                } else {
                    editTextPasswordConfirm.setBackgroundResource(R.drawable.vao_edittext);
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
        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void onclickChkBox(View view) {
        if (!isCheckBoxChecked) {
            isCheckBoxChecked = true;
            checkBox.setImageResource(R.drawable.vao_icon_06);
            checkBox.setScaleType(ImageButton.ScaleType.CENTER_CROP);
        } else {
            isCheckBoxChecked = false;
            checkBox.setImageResource(R.drawable.vao_chkbox);
            checkBox.setScaleType(ImageButton.ScaleType.CENTER_CROP);
        }
    }

    public void onclickJoinNext(View view) {
        boolean isId, isPassword, isPasswordConfirm;

        if (editTextId.getText().toString().length() < 2) {
            editTextId.setBackgroundResource(R.drawable.vao_edittext_red);
            idErrorMsg.setVisibility(View.VISIBLE);
            isId = false;
        } else {
            editTextId.setBackgroundResource(R.drawable.vao_edittext);
            idErrorMsg.setVisibility(View.INVISIBLE);
            isId = true;
        }

        if (editTextPassword.getText().toString().length() < 2) {
            editTextPassword.setBackgroundResource(R.drawable.vao_edittext_red);
            passwordErrorMsg.setVisibility(View.VISIBLE);
            isPassword = false;
        } else {
            editTextPassword.setBackgroundResource(R.drawable.vao_edittext);
            passwordErrorMsg.setVisibility(View.INVISIBLE);
            isPassword = true;
        }

        if (editTextPassword.getText().toString().equals(editTextPasswordConfirm.getText().toString())) {
            editTextPasswordConfirm.setBackgroundResource(R.drawable.vao_edittext);
            passwordConfirmErrorMsg.setVisibility(View.INVISIBLE);
            isPasswordConfirm = true;
        } else {
            editTextPasswordConfirm.setBackgroundResource(R.drawable.vao_edittext_red);
            passwordConfirmErrorMsg.setVisibility(View.VISIBLE);
            isPasswordConfirm = false;
        }

        if (!isCheckBoxChecked) {
            checkBox.setImageResource(R.drawable.vao_edittext_red);
        }

        if (isId && isPassword && isPasswordConfirm && isCheckBoxChecked) {
            startActivityForResult(new Intent(getApplicationContext(), DimLoadingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), 1003);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RetrofitAuthService.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitAuthService retrofitAuthService = retrofit.create(RetrofitAuthService.class);

            Map<String, String> checkData = new HashMap<>();
            checkData.put("username", editTextId.getText().toString());

            retrofitAuthService.authCheck(checkData).enqueue(new Callback<RetrofitAuthData>() {
                @Override
                public void onResponse(Call<RetrofitAuthData> call, Response<RetrofitAuthData> response) {
                    if (response.isSuccessful()) {
                        RetrofitAuthData body = response.body();
                        if (body != null) {
                            Toast.makeText(getApplicationContext(), body.getMessage(), Toast.LENGTH_SHORT).show();

                            if (body.getSuccess()) {
                                finishActivity(1003);
                                Intent intent = new Intent(JoinActivity.this, TwoWayPasswordAcitivty.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("username", editTextId.getText().toString());
                                intent.putExtra("password", editTextPassword.getText().toString());
                                startActivity(intent);
                                finish();
                            } else {
                                finishActivity(1003);
                                editTextId.setBackgroundResource(R.drawable.vao_edittext_red);
                                idErrorMsg.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<RetrofitAuthData> call, Throwable t) {

                }
            });
        }
    }

    public void onclickGoLoginSub(View view) {
        Intent intent = new Intent(JoinActivity.this, LoginSubActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
