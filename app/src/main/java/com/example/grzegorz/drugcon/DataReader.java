package com.example.grzegorz.drugcon;

import android.content.Context;

/**
 * Created by Grzegorz on 2017-05-29.
 */

public class DataReader {

    private final Context context;

    public DataReader(Context context){
        this.context=context;
    }

    public Context getContext() {
        return context;
    }
}
