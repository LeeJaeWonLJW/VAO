package vao.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vao.wallet.server.RetrofitUserInfoData;
import vao.wallet.server.RetrofitUserInfoService;

public class MainSubActivity extends AppCompatActivity {

    BottomNavigationView vaoNavigationView;
    Button send_0, send_1, send_2, send_3, send_4, send_5, send_6, send_7, send_8, send_9, send_dot;
    ImageButton btnSendBack;
    ImageButton tokenCardMain;
    Button btnSendBoxSend, btnSendBoxCancel;

    TextView textTokenSendValue;
    View textTokenSendValueBar;

    ViewPager viewPager;

    String myPassword = "";
    String token;
    String myWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sub);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorStatusBar1));
        window.setNavigationBarColor(getResources().getColor(R.color.colorGray));

        vaoNavigationView = (BottomNavigationView) findViewById(R.id.vaoNavigationView);
        removeShiftMode(vaoNavigationView);
        vaoNavigationView.setSelectedItemId(R.id.bottom_navigator_send);
        vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send_blue);
        vaoNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.bottom_navigator_notice:
                                Intent intent = new Intent(getApplicationContext(), NoticeAcitvity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                finish();                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice_blue);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_gift).setIcon(R.drawable.vao_nav_gift);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_wallet).setIcon(R.drawable.vao_nav_wallet);
                                return true;

                            case R.id.bottom_navigator_send:
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send_blue);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_gift).setIcon(R.drawable.vao_nav_gift);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_wallet).setIcon(R.drawable.vao_nav_wallet);
                                return true;

                            case R.id.bottom_navigator_gift:
                                Toast.makeText(getApplicationContext(), "gift", Toast.LENGTH_SHORT).show();
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_gift).setIcon(R.drawable.vao_nav_gift_blue);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_wallet).setIcon(R.drawable.vao_nav_wallet);
                                return true;

                            case R.id.bottom_navigator_wallet:
                                Toast.makeText(getApplicationContext(), "wallet", Toast.LENGTH_SHORT).show();
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_gift).setIcon(R.drawable.vao_nav_gift);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_wallet).setIcon(R.drawable.vao_nav_wallet_blue);
                                return true;
                        }

                        return false;
                    }
                }
        );

        /*
        tokenCardMain = (ImageButton) findViewById(R.id.tokenCardMain);
        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.token_kp_symbol)).getBitmap();
        Drawable drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() * (28.0 / bitmap.getHeight())), 28, true));
        tokenCardMain.setImageDrawable(drawable);
        tokenCardMain.setScaleType(ImageButton.ScaleType.CENTER);
        */

        btnSendBack = (ImageButton) findViewById(R.id.btnSendBack);
        Bitmap bitmap2 = ((BitmapDrawable) getResources().getDrawable(R.drawable.vao_icon_keyboard_back)).getBitmap();
        Drawable drawable2 = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap2, 21, 17, true));
        btnSendBack.setImageDrawable(drawable2);
        btnSendBack.setScaleType(ImageButton.ScaleType.CENTER);

        send_0 = (Button) findViewById(R.id.send_0);
        send_1 = (Button) findViewById(R.id.send_1);
        send_2 = (Button) findViewById(R.id.send_2);
        send_3 = (Button) findViewById(R.id.send_3);
        send_4 = (Button) findViewById(R.id.send_4);
        send_5 = (Button) findViewById(R.id.send_5);
        send_6 = (Button) findViewById(R.id.send_6);
        send_7 = (Button) findViewById(R.id.send_7);
        send_8 = (Button) findViewById(R.id.send_8);
        send_9 = (Button) findViewById(R.id.send_9);
        send_dot = (Button) findViewById(R.id.send_dot);

        KeyboardClickListener keyboardClickListener = new KeyboardClickListener();
        send_0.setOnClickListener(keyboardClickListener);
        send_1.setOnClickListener(keyboardClickListener);
        send_2.setOnClickListener(keyboardClickListener);
        send_3.setOnClickListener(keyboardClickListener);
        send_4.setOnClickListener(keyboardClickListener);
        send_5.setOnClickListener(keyboardClickListener);
        send_6.setOnClickListener(keyboardClickListener);
        send_7.setOnClickListener(keyboardClickListener);
        send_8.setOnClickListener(keyboardClickListener);
        send_9.setOnClickListener(keyboardClickListener);
        send_dot.setOnClickListener(keyboardClickListener);

        btnSendBoxSend = (Button) findViewById(R.id.sendBoxSend);
        btnSendBoxCancel = (Button) findViewById(R.id.sendBoxCancel);

        textTokenSendValue = (TextView) findViewById(R.id.textTokenSendValue);
        textTokenSendValueBar = (View) findViewById(R.id.textTokenSendValueBar);
        viewPager = findViewById(R.id.viewPager);
        TokenFragmentAdapter tokenFragmentAdapter = new TokenFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tokenFragmentAdapter);

        viewPager.setClipToPadding(false);
        int dpValue = 16;
        float d = getResources().getDisplayMetrics().density;
        int margin = (int)(dpValue * d);
        viewPager.setPadding(margin, 0, margin, 0);
        viewPager.setPageMargin(margin);

        ArrayList<Integer> listImage = new ArrayList<>();
        listImage.add(R.drawable.token_kp_symbol);
        listImage.add(R.drawable.token_kaki_symbol);
        listImage.add(R.drawable.token_eth_symbol);

        for (int i = 0; i < 3; i++) {
            TokenFragment tokenFragment = new TokenFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("imgRes", listImage.get(i));
            tokenFragment.setArguments(bundle);
            tokenFragmentAdapter.addItem(tokenFragment);
        }
        tokenFragmentAdapter.notifyDataSetChanged();

        Intent tokenData = getIntent();
        token = tokenData.getStringExtra("token");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitUserInfoService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitUserInfoService retrofitUserInfoService = retrofit.create(RetrofitUserInfoService.class);

        retrofitUserInfoService.userInfo(token).enqueue(new Callback<RetrofitUserInfoData>() {
            @Override
            public void onResponse(Call<RetrofitUserInfoData> call, Response<RetrofitUserInfoData> response) {
                if (response.isSuccessful()) {
                    RetrofitUserInfoData body = response.body();
                    if (body != null) {
                        JsonObject message = body.getMessage();
                        myWallet = message.get("wallet").getAsString();
                    }
                }
            }

            @Override
            public void onFailure(Call<RetrofitUserInfoData> call, Throwable t) {

            }
        });

    }

    protected void showSendBox() {
        btnSendBoxCancel.setVisibility(View.VISIBLE);
        btnSendBoxSend.setVisibility(View.VISIBLE);
        vaoNavigationView.setVisibility(View.INVISIBLE);
        textTokenSendValueBar.setBackgroundColor(getColor(R.color.colorBlue));
        textTokenSendValue.setTextColor(getColor(R.color.colorBlue));
    }

    protected void hideSendBox() {
        btnSendBoxCancel.setVisibility(View.INVISIBLE);
        btnSendBoxSend.setVisibility(View.INVISIBLE);
        vaoNavigationView.setVisibility(View.VISIBLE);
        textTokenSendValueBar.setBackgroundColor(getColor(R.color.colorGray));
        textTokenSendValue.setTextColor(getColor(R.color.colorLightBlueGray));
    }

    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShifting(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
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

    public void onclickSendBackspace(View view) {
        if (myPassword.length() > 0)
            myPassword = myPassword.substring(0, myPassword.length() - 1);
        isShow();
    }

    public void onclickSendBoxCancel(View view) {
        hideSendBox();
    }

    public void onclickSendBoxSend(View view) {
        Intent intent = new Intent(getApplicationContext(), SendWhoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("cost", myPassword);
        intent.putExtra("myAddress", myWallet);
        startActivity(intent);
    }

    class KeyboardClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            showSendBox();

            switch (view.getId()) {
                case R.id.send_0:
                    myPassword += "0";
                    break;

                case R.id.send_1:
                    myPassword += "1";
                    break;

                case R.id.send_2:
                    myPassword += "2";
                    break;

                case R.id.send_3:
                    myPassword += "3";
                    break;

                case R.id.send_4:
                    myPassword += "4";
                    break;

                case R.id.send_5:
                    myPassword += "5";
                    break;

                case R.id.send_6:
                    myPassword += "6";
                    break;

                case R.id.send_7:
                    myPassword += "7";
                    break;

                case R.id.send_8:
                    myPassword += "8";
                    break;

                case R.id.send_9:
                    myPassword += "9";
                    break;

                case R.id.send_dot:
                    myPassword += ".";
                    break;
            }
            isShow();
        }
    }

    public void isShow() {
        //Toast.makeText(getApplicationContext(), myPassword, Toast.LENGTH_SHORT).show();
        textTokenSendValue.setText(myPassword + " KP");
    }

    class TokenFragmentAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments = new ArrayList<>();

        TokenFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        void addItem(Fragment fragment) {
            fragments.add(fragment);
        }
    }
}
