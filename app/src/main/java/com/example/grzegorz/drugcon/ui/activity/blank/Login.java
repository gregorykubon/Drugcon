package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.LoginView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.LoginPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.IOException;

public class Login extends MvpActivity implements LoginView {
    public static final String TAG = "Login";
    @InjectPresenter
    LoginPresenter mLoginPresenter;


    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Login.class);

        return intent;
    }


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    try{
                        mLoginPresenter.attemptLogin(mEmailView.getText().toString(),mPasswordView.getText().toString());
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                    return true;
                }
                return false;
            }
        });

        Button mLoginButton = (Button) findViewById(R.id.button10);
        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    mLoginPresenter.attemptLogin(mEmailView.getText().toString(),mPasswordView.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.button12);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public void showInvalid(){
       //TODO invalid Toast.makeText()
    }

}
