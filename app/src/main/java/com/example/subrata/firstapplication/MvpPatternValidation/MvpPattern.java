package com.example.subrata.firstapplication.MvpPatternValidation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.subrata.firstapplication.MvpPatternValidation.Presenter.ILoginPresenter;
import com.example.subrata.firstapplication.MvpPatternValidation.Presenter.LoginPresenter;
import com.example.subrata.firstapplication.MvpPatternValidation.View.ILoginView;
import com.example.subrata.firstapplication.R;

public class MvpPattern extends AppCompatActivity implements ILoginView {

    private EditText edt_email, edt_password;
    private Button btn_login;

    ILoginPresenter iLoginPresenter;

    private void init()
    {
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_pattern);

        init();

        iLoginPresenter = new LoginPresenter(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iLoginPresenter.onLogin(edt_email.getText().toString().trim(), edt_password.getText().toString().trim());
            }
        });
    }

    @Override
    public void onLoginResult(String massage) {

        Toast.makeText(this, ""+massage, Toast.LENGTH_SHORT).show();
    }
}
