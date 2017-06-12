package com.example.grzegorz.drugcon.ui.activity.blank;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.OverScroller;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

    FloatingActionButton fab;

    // ArrayList for Listview
    //ArrayList<HashMap<String, String>> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final DataReader dr = new DataReader(this);

       // String products[] = mListPresenter.getList(getIntent().getExtras().getString("login"),dr);

        lv = (android.widget.ListView) findViewById(R.id.list_view1);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.content_list, R.id.product_name, mListPresenter.getList(getIntent().getExtras().getString("login"),dr));
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                final String drug = ((TextView) view.findViewById(R.id.product_name)).getText().toString();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        List.this );

                // set title
                alertDialogBuilder.setTitle("Confirmation");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you want to delete this to your list?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                mListPresenter.delete(getIntent().getExtras().getString("login"),drug,dr);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(List.this);
                View mView = getLayoutInflater().inflate(R.layout.fragment_add_dialog,null);
                mBuilder.setTitle("Add a medicine");
                final CheckBox monday = (CheckBox) mView.findViewById(R.id.checkBox);
                final CheckBox tuesday = (CheckBox) mView.findViewById(R.id.checkBox2);
                final CheckBox wednesday = (CheckBox) mView.findViewById(R.id.checkBox3);
                final CheckBox thursday = (CheckBox) mView.findViewById(R.id.checkBox4);
                final CheckBox friday = (CheckBox) mView.findViewById(R.id.checkBox5);
                final CheckBox saturday = (CheckBox) mView.findViewById(R.id.checkBox6);
                final CheckBox sunday = (CheckBox) mView.findViewById(R.id.checkBox7);
            //    final EditText times = (EditText) mView.findViewById(R.id.editText);
                final EditText form = (EditText) mView.findViewById(R.id.textView2);



                final Spinner spinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(List.this,android.R.layout.simple_spinner_item,mListPresenter.getNames(dr));
                spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(spinneradapter);
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
                        if(!mListPresenter.checkInteraction(getIntent().getExtras().getString("login"),spinner.getSelectedItem().toString(),dr).equalsIgnoreCase("OK")){
                            Toast.makeText(List.this,mListPresenter.checkInteraction(getIntent().getExtras().getString("login"),spinner.getSelectedItem().toString(),dr),Toast.LENGTH_LONG).show();
                            mListPresenter.addToList(getIntent().getExtras().getString("login"),spinner.getSelectedItem().toString(),days,dr);
                        }else{
                            mListPresenter.addToList(getIntent().getExtras().getString("login"),spinner.getSelectedItem().toString(),days,dr);
                        }

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
    }
}
