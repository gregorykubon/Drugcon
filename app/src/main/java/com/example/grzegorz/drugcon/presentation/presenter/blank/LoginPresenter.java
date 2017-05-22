package com.example.grzegorz.drugcon.presentation.presenter.blank;


import android.content.Context;
import android.database.Cursor;

import com.example.grzegorz.drugcon.LoginModel;
import com.example.grzegorz.drugcon.presentation.view.blank.LoginView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.io.IOException;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {


    public boolean attemptLogin(String name, String pass) throws IOException {
        Context context = null;
        LoginModel db = new LoginModel(context);
        db.createDataBase();
        db.openDataBase();
        String[] args = {name,pass};
        Cursor c = db.query("Account",null,"login=? AND password=?",args,null,null,null);
        if(c.getColumnCount()>0){
            return true;
        }
        return false;
    }
}


