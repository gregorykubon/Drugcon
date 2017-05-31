package com.example.grzegorz.drugcon.ui.activity.blank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.ListView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.ListPresenter;
import com.example.grzegorz.drugcon.R;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;

public class List extends MvpActivity implements ListView {
    public static final String TAG = "List";
    @InjectPresenter
    ListPresenter mListPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, List.class);

        return intent;
    }

    // List view
    private android.widget.ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText listSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final DataReader dr = new DataReader(this);

        String products[] = mListPresenter.getList(getIntent().getExtras().getString("login"),dr);


        lv = (android.widget.ListView) findViewById(R.id.list_view1);
        listSearch = (EditText) findViewById(R.id.listSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.content_list, R.id.product_name, products);
        lv.setAdapter(adapter);

    }
}
