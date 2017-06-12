package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.SettingsView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.SettingsPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Settings extends MvpActivity implements SettingsView {
    public static final String TAG = "Settings";
    @InjectPresenter
    SettingsPresenter mSettingsPresenter;






    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Settings.class);

        return intent;
    }

    // Lists view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordnewView;
    private EditText mPasswordNewRepeatView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String loginName = getIntent().getExtras().getString("login");


        setContentView(R.layout.activity_settings);


        Button mListButton = (Button) findViewById(R.id.button_list);
        mListButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //try {

                Intent intent = new Intent(getApplicationContext(), Show_List.class);
                intent.putExtra("login",getIntent().getExtras().getString("login"));
                startActivity(intent);

                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        Button mSettingsButton = (Button) findViewById(R.id.button_settings1);
        mSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //try {

                        Intent intent = new Intent(getApplicationContext(), Change_Delete.class);
                        intent.putExtra("login",getIntent().getExtras().getString("login"));
                        startActivity(intent);

                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        Button mUpdateButton = (Button) findViewById(R.id.button_settings2);
        mUpdateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //try {

                Intent intent = new Intent(getApplicationContext(), Change_Update.class);
                intent.putExtra("login",getIntent().getExtras().getString("login"));
                startActivity(intent);

                /*} catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });




    }


}
