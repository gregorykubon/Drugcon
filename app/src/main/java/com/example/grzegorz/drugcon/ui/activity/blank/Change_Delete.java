package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.presenter.blank.Change_DeletePresenter;
import com.example.grzegorz.drugcon.presentation.view.blank.Change_DeleteView;
import com.example.grzegorz.drugcon.presentation.view.blank.MenuAccView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.MenuAccPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.IOException;

public class Change_Delete extends MvpActivity implements Change_DeleteView {
    public static final String TAG = "Change_Delete";
    @InjectPresenter
    Change_DeletePresenter mChange_DeletePresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Change_Delete.class);

        return intent;
    }


    private EditText mPasswordnewView;
    private EditText mPasswordNewRepeatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_acc);
        setContentView(R.layout.activity_change_delete);

        final DataReader dr = new DataReader(this);

        final String loginName = getIntent().getExtras().getString("login");

       /* ImageButton button_list = (ImageButton) findViewById(R.id.button_list);
        button_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lists.class);
                intent.putExtra("login",getIntent().getExtras().getString("login"));
                startActivity(intent);

            }
        });
        ImageButton button_alarm = (ImageButton) findViewById(R.id.button_alarm);

        button_alarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alarm.class);
                startActivity(intent);

            }
        });
        ImageButton button_search = (ImageButton) findViewById(R.id.button_search);
        button_search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                intent.putExtra("login",getIntent().getExtras().getString("login"));
                startActivity(intent);

            }
        });

        ImageButton button_settings = (ImageButton) findViewById(R.id.button_settings);
        button_settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                intent.putExtra("login",getIntent().getExtras().getString("login"));
                startActivity(intent);

            }
        });*/

        mPasswordnewView = (EditText) findViewById(R.id.editText_password_delete);
        mPasswordNewRepeatView = (EditText) findViewById(R.id.editText_confirm_password_delete);

        Button mDeleteButton = (Button) findViewById(R.id.button_delete_pass);
        mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (mPasswordnewView.getText().toString().equals(mPasswordNewRepeatView.getText().toString())) {


                    try {
                        if (mChange_DeletePresenter.deleteAccount(loginName, mPasswordnewView.getText().toString(), dr) == true) {
                            Toast.makeText(Change_Delete.this, "Account Deleted", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);

                        } else
                            Toast.makeText(Change_Delete.this, "Something went wrong", Toast.LENGTH_LONG).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else Toast.makeText(Change_Delete.this, "Passwords are not the same", Toast.LENGTH_LONG).show();

            }
        });
    }
}