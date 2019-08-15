package vao.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static vao.wallet.MainSubActivity.removeShiftMode;

public class NoticeAcitvity extends AppCompatActivity {
    BottomNavigationView vaoNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.colorStatusBar1));
        window.setNavigationBarColor(getResources().getColor(R.color.colorGray));

        vaoNavigationView = (BottomNavigationView) findViewById(R.id.vaoNavigationView);
        removeShiftMode(vaoNavigationView);
        vaoNavigationView.setSelectedItemId(R.id.bottom_navigator_notice);
        vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice_blue);
        vaoNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.bottom_navigator_notice:
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_notice).setIcon(R.drawable.vao_nav_notice_blue);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_send).setIcon(R.drawable.vao_nav_send);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_gift).setIcon(R.drawable.vao_nav_gift);
                                vaoNavigationView.getMenu().findItem(R.id.bottom_navigator_wallet).setIcon(R.drawable.vao_nav_wallet);
                                return true;

                            case R.id.bottom_navigator_send:
                                Intent intent = new Intent(getApplicationContext(), MainSubActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                                finish();
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
    }

    @Override
    public void onBackPressed() {
        // pass
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
