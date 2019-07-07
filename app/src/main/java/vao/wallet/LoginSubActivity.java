package vao.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

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
        // TODO: API Connection
        if (editTextPassword.getText().toString().length() > 2) {
            idErrorMsg.setVisibility(View.INVISIBLE);
            passwordErrorMsg.setVisibility(View.INVISIBLE);
        } else {
            editTextId.setBackgroundResource(R.drawable.vao_edittext_red);
            editTextPassword.setBackgroundResource(R.drawable.vao_edittext_red);

            idErrorMsg.setVisibility(View.VISIBLE);
            passwordErrorMsg.setVisibility(View.VISIBLE);
        }
    }

    public void onclickMakeWallet(View view) {
        Intent intent = new Intent(LoginSubActivity.this, JoinActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }
}
