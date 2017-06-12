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
import com.example.grzegorz.drugcon.presentation.presenter.blank.Show_ListPresenter;
import com.example.grzegorz.drugcon.presentation.view.blank.SettingsView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.SettingsPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.grzegorz.drugcon.presentation.view.blank.Show_ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Show_List extends MvpActivity implements Show_ListView {
    public static final String TAG = "Show_List";
    @InjectPresenter
    Show_ListPresenter mShow_ListPresenter;






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


        setContentView(R.layout.activity_show_list);

        final DataReader dr = new DataReader(this);

        String products[] = mShow_ListPresenter.getAll(loginName,dr);


        lv = (ListView) findViewById(R.id.list_view10);

        adapter = new ArrayAdapter<String>(this, R.layout.content_list, R.id.product_name, products);
        lv.setAdapter(adapter);







    }


}
