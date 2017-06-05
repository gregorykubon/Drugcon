package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.R;
import com.example.grzegorz.drugcon.presentation.view.blank.SearchView;
import com.example.grzegorz.drugcon.presentation.presenter.blank.SearchPresenter;

import com.arellomobile.mvp.MvpActivity;


import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.HashMap;

public class Search extends MvpActivity implements SearchView {
    public static final String TAG = "Search";
    @InjectPresenter
    SearchPresenter mSearchPresenter;

    public static Intent getIntent(final Context context) {
        Intent intent = new Intent(context, Search.class);

        return intent;
    }

    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;

    // Search EditText
    EditText inputSearch;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final DataReader dr = new DataReader(this);

        String products[] = mSearchPresenter.getAll(dr);


        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.content_list, R.id.product_name, products);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Search.this);
                View mView = getLayoutInflater().inflate(R.layout.fragment_add_dialog,null);
                mBuilder.setTitle("Add "+parent.getAdapter().getItem(position).toString()+" to list");
                final CheckBox monday = (CheckBox) mView.findViewById(R.id.checkBox);
                final CheckBox tuesday = (CheckBox) mView.findViewById(R.id.checkBox2);
                final CheckBox wednesday = (CheckBox) mView.findViewById(R.id.checkBox3);
                final CheckBox thursday = (CheckBox) mView.findViewById(R.id.checkBox4);
                final CheckBox friday = (CheckBox) mView.findViewById(R.id.checkBox5);
                final CheckBox saturday = (CheckBox) mView.findViewById(R.id.checkBox6);
                final CheckBox sunday = (CheckBox) mView.findViewById(R.id.checkBox7);
                //    final EditText times = (EditText) mView.findViewById(R.id.editText);
                final EditText form = (EditText) mView.findViewById(R.id.textView2);




                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String days = "days";
                        if(monday.isChecked()){days=days.concat("1");}else days=days.concat("0");
                        if(tuesday.isChecked()){days=days.concat("2");}else days=days.concat("0");
                        if(wednesday.isChecked()){days=days.concat("3");}else days=days.concat("0");
                        if(thursday.isChecked()){days=days.concat("4");}else days=days.concat("0");
                        if(friday.isChecked()) {days=days.concat("5");}else days=days.concat("0");
                        if(saturday.isChecked()) {days=days.concat("6");}else days=days.concat("0");
                        if(sunday.isChecked()){days=days.concat("7");}else days=days.concat("0");
                        if(!form.getText().toString().isEmpty()) days=days.concat("XXX"+form.getText().toString()+"YYY");
                        mSearchPresenter.addToList(getIntent().getExtras().getString("login"),parent.getAdapter().getItem(position).toString(),days,dr);
                        //        adapter.add(spinner.getSelectedItem().toString());
                        //       lv.setAdapter(adapter);
                    }
                });
                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Search.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

}