package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

import com.example.grzegorz.drugcon.DataReader;
import com.example.grzegorz.drugcon.DatabaseModel;
import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.ListView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.grzegorz.drugcon.ui.activity.blank.List;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

@InjectViewState
public class ListPresenter extends MvpPresenter<ListView> {

    public String[] getList(String login,DataReader dr) {
        Cursor c = null;
        LoginModel myDb = new LoginModel(dr.getContext());
        try {
            myDb.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDb.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        c = myDb.query("Account",null,null,null,null,null,null);
        String products[] = new String[c.getCount()];
        c.moveToFirst();
        do{
            if(c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                products = c.getString(c.getColumnIndex("list")).substring(c.getString(c.getColumnIndex("list")).indexOf(",")+1).split(",");
            }
        }while(c.moveToNext());

        int i = 1;
        while(i<products.length){
            products[i]=products[i].substring(0,products[i].indexOf(";"));
            i++;
        }

        String[] yourArray = Arrays.copyOfRange(products, 1, products.length);

        c.close();
        myDb.close();

        return yourArray;

    }


    public String[] getNames(DataReader dr) {
            Cursor c = null;
            DatabaseModel myDb = new DatabaseModel(dr.getContext());
            try {
                myDb.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }
            try {
                myDb.openDataBase();
            } catch (SQLException sqle) {
                throw sqle;
            }

            c = myDb.query("Drug",null,null,null,null,null,null);
            String products[] = new String[c.getCount()];
            c.moveToFirst();
            int i=0;
            do{
                products[i]=c.getString(c.getColumnIndex("name"));
                i++;
            }while(c.moveToNext());

            c.close();
            myDb.close();

            return products;


    }

    public void addToList(String login, String drug, String days, DataReader dr) {
            Cursor c = null;
            LoginModel myDb = new LoginModel(dr.getContext());
            try {
                myDb.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }
            try {
                myDb.openDataBase();
            } catch (SQLException sqle) {
                throw sqle;
            }
            //     c = myDb.query("Account", null, null, null, null, null, null);
            try {
                c.moveToFirst();
            } catch (Exception e) {
                e.printStackTrace();
            }

            c = myDb.query("Account", null, null,null, null, null, null);
            c.moveToFirst();
            String toUpdate = null;

            do{
                if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                    //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                    toUpdate = c.getString(c.getColumnIndex("list")).toString();
                }
            }while(c.moveToNext());

            toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(","+drug+";"+days+";"+ DateFormat.getDateTimeInstance().format(new Date())).toString();

            ContentValues cv = new ContentValues();
            cv.put("list",toUpdate);
            cv.put("history",toUpdate);
            myDb.getWritableDatabase().update("Account",cv,"login='"+login+"'",null);

            c.close();
            myDb.close();

    }

    public void delete(String login, String drug, DataReader dr) {
        Cursor c = null;
        LoginModel myDb = new LoginModel(dr.getContext());
        try {
            myDb.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDb.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        //     c = myDb.query("Account", null, null, null, null, null, null);
        try {
            c.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = myDb.query("Account", null, null,null, null, null, null);
        c.moveToFirst();
        String toUpdate = null;

        do{
            if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                toUpdate = c.getString(c.getColumnIndex("list")).toString();
            }
        }while(c.moveToNext());

        String part1 = toUpdate.substring(0,toUpdate.indexOf(drug));
        String part2 = toUpdate.substring(toUpdate.indexOf(drug));
        if(part2.contains(",")){
            part2 = part2.substring(part2.indexOf(",")+1);

        }else part2 = ",";
        toUpdate = part1.concat(part2);
        if(toUpdate.equalsIgnoreCase(",,,"))toUpdate=",";
        ContentValues cv = new ContentValues();
        cv.put("list",toUpdate);

        myDb.getWritableDatabase().update("Account",cv,"login='"+login+"'",null);

        c.close();
        myDb.close();
    }

    public boolean checkInteraction(String login, String drug, DataReader dr) {
        Cursor c = null;
        LoginModel myDb = new LoginModel(dr.getContext());
        try {
            myDb.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDb.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        //     c = myDb.query("Account", null, null, null, null, null, null);
        try {
            c.moveToFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

        c = myDb.query("Account", null, null,null, null, null, null);
        c.moveToFirst();
        String list = null;

        do{
            if (c.getString(c.getColumnIndex("login")).equalsIgnoreCase(login)){
                //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                list = c.getString(c.getColumnIndex("list")).toString();

            }
        }while(c.moveToNext());

        if(list.equalsIgnoreCase(","))return true;


        String[] products = list.split(",");
        products = Arrays.copyOfRange(products,2,products.length);
        for(String string:products){
            string = string.substring(0,string.indexOf(";"));
            c = null;
            DatabaseModel myDb2 = new DatabaseModel(dr.getContext());
            try {
                myDb2.createDataBase();
            } catch (IOException ioe) {
                throw new Error("Unable to create database");
            }
            try {
                myDb2.openDataBase();
            } catch (SQLException sqle) {
                throw sqle;
            }

            c = myDb2.query("Drug",null,null,null,null,null,null);
            c.moveToFirst();
            String id = null;
            do{
                if (c.getString(c.getColumnIndex("name")).equalsIgnoreCase(drug)){
                    //  toUpdate = new StringBuilder(String.valueOf(toUpdate)).append(c.getString(c.getColumnIndex("list"))).toString();
                    id = c.getString(c.getColumnIndex("_id")).toString();

                }
            }while(c.moveToNext());


            c = myDb2.query("Drug_Interaction",null,null,null,null,null,null);
            c.moveToFirst();
            myDb2.close();
            do{
                if(c.getString(c.getColumnIndex("drug_id")).toString().equalsIgnoreCase(id)){
                    c.close();
                    return false;
                }
            }while(c.moveToNext());



        }

        c.close();

        return true;
    }
}
