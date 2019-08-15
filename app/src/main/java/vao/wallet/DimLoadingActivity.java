package vao.wallet;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.victor.loading.rotate.RotateLoading;

public class DimLoadingActivity extends Activity {
    protected RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dim_loading);

        rotateLoading = (RotateLoading) findViewById(R.id.rotateLoading);
        rotateLoading.start();
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
}
