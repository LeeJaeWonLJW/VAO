package vao.wallet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

public class TwoWayPasswordAcitivty extends AppCompatActivity {

    TextView passBarOne, passBarTwo, passBarThree, passBarFour;
    Button password_0, password_1, password_2, password_3, password_4, password_5, password_6, password_7, password_8, password_9;
    ImageButton btnKeyBack;

    String userUsername, userPassword;

    int idsCount = 0;
    String myPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_way_password);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorStatusBar1));
        window.setNavigationBarColor(getResources().getColor(R.color.colorNavbarDefault));

        btnKeyBack = (ImageButton) findViewById(R.id.btnKeyBack);
        /*
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.vao_icon_keyboard_back);
        Bitmap bitmapScaled = Bitmap.createScaledBitmap(bitmap, 21, 17, true);
        btnKeyBack.setImageBitmap(bitmapScaled);
        */
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.vao_icon_keyboard_back)).getBitmap();
        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 21, 17, true));
        btnKeyBack.setImageDrawable(drawable);
        btnKeyBack.setScaleType(ImageButton.ScaleType.CENTER);

        password_0 = (Button) findViewById(R.id.password_0);
        password_1 = (Button) findViewById(R.id.password_1);
        password_2 = (Button) findViewById(R.id.password_2);
        password_3 = (Button) findViewById(R.id.password_3);
        password_4 = (Button) findViewById(R.id.password_4);
        password_5 = (Button) findViewById(R.id.password_5);
        password_6 = (Button) findViewById(R.id.password_6);
        password_7 = (Button) findViewById(R.id.password_7);
        password_8 = (Button) findViewById(R.id.password_8);
        password_9 = (Button) findViewById(R.id.password_9);

        passBarOne = (TextView) findViewById(R.id.passBarOne);
        passBarTwo = (TextView) findViewById(R.id.passBarTwo);
        passBarThree = (TextView) findViewById(R.id.passBarThree);
        passBarFour = (TextView) findViewById(R.id.passBarFour);

        KeyboardClickListener keyboardClickListener = new KeyboardClickListener();
        password_0.setOnClickListener(keyboardClickListener);
        password_1.setOnClickListener(keyboardClickListener);
        password_2.setOnClickListener(keyboardClickListener);
        password_3.setOnClickListener(keyboardClickListener);
        password_4.setOnClickListener(keyboardClickListener);
        password_5.setOnClickListener(keyboardClickListener);
        password_6.setOnClickListener(keyboardClickListener);
        password_7.setOnClickListener(keyboardClickListener);
        password_8.setOnClickListener(keyboardClickListener);
        password_9.setOnClickListener(keyboardClickListener);

        Intent getData = getIntent();
        userUsername = getData.getStringExtra("username");
        userPassword = getData.getStringExtra("password");
        Log.e("DATA-TEST", userUsername + " | " + userPassword);
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

    public void onclickKeyboardBackspace(View view) {
        isDisShow();
        if (idsCount > 0)
            myPassword = myPassword.substring(0, myPassword.length() - 1);
        idsCount--;
    }

    class KeyboardClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.password_0:
                    idsCount++;
                    myPassword += "0";
                    break;

                case R.id.password_1:
                    idsCount++;
                    myPassword += "1";
                    break;

                case R.id.password_2:
                    idsCount++;
                    myPassword += "2";
                    break;

                case R.id.password_3:
                    idsCount++;
                    myPassword += "3";
                    break;

                case R.id.password_4:
                    idsCount++;
                    myPassword += "4";
                    break;

                case R.id.password_5:
                    idsCount++;
                    myPassword += "5";
                    break;

                case R.id.password_6:
                    idsCount++;
                    myPassword += "6";
                    break;

                case R.id.password_7:
                    idsCount++;
                    myPassword += "7";
                    break;

                case R.id.password_8:
                    idsCount++;
                    myPassword += "8";
                    break;

                case R.id.password_9:
                    idsCount++;
                    myPassword += "9";
                    break;

                    /*
                case R.id.btnKeyBack:
                    idsCount--;
                    if (idsCount > 0)
                        myPassword = myPassword.substring(0, myPassword.length() - 1);
                    break;
                    */
            }
            isShow();
        }
    }

    public void onclickLoginSubExit(View view) {
        Intent intent = new Intent(TwoWayPasswordAcitivty.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

    public void isShow() {
        switch (idsCount) {
            case 1:
                passBarOne.setVisibility(View.VISIBLE);
                break;

            case 2:
                passBarTwo.setVisibility(View.VISIBLE);
                break;

            case 3:
                passBarThree.setVisibility(View.VISIBLE);
                break;

            case 4:
                passBarFour.setVisibility(View.VISIBLE);
                startActivityForResult(new Intent(getApplicationContext(), DimLoadingActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION), 1003);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(RetrofitAuthService.URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitAuthService retrofitAuthService = retrofit.create(RetrofitAuthService.class);

                Map<String, String> registerData = new HashMap<>();
                registerData.put("username", userUsername);
                registerData.put("password", userPassword);
                registerData.put("pinNumber", myPassword);

                retrofitAuthService.authRegister(registerData).enqueue(new Callback<RetrofitAuthData>() {
                    @Override
                    public void onResponse(Call<RetrofitAuthData> call, Response<RetrofitAuthData> response) {
                        if (response.isSuccessful()) {
                            RetrofitAuthData body = response.body();
                            if (body != null) {
                                Toast.makeText(getApplicationContext(), body.getMessage(), Toast.LENGTH_SHORT).show();

                                if (body.getSuccess()) {
                                    finishActivity(1003);
                                    Intent intent = new Intent(TwoWayPasswordAcitivty.this, MainSubActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RetrofitAuthData> call, Throwable t) {

                    }
                });
                break;
        }
    }

    public void isDisShow() {
        switch (idsCount) {
            case 1:
                passBarOne.setVisibility(View.INVISIBLE);
                break;

            case 2:
                passBarTwo.setVisibility(View.INVISIBLE);
                break;

            case 3:
                passBarThree.setVisibility(View.INVISIBLE);
                break;
        }
    }
}