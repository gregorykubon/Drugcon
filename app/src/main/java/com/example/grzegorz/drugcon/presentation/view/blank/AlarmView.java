package com.example.grzegorz.drugcon.presentation.view.blank;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.*;

import com.arellomobile.mvp.MvpView;
import com.example.grzegorz.drugcon.DataReader;

public interface AlarmView extends MvpView {

    //ListView getListView();
    //void setListAdapter(ListAdapter adapter);
   // ListAdapter getListAdapter();

   void setAl(DataReader dr);
  void  getCheckedDays();


}
