package com.example.grzegorz.drugcon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Grzegorz on 2017-05-22.
 */

public class LoginModel extends SQLiteOpenHelper{

        //The Android's default system path of your application database.
        String DB_PATH = null;

        private static String DB_NAME = "account.db";

        private SQLiteDatabase myDataBase;

        private final Context myContext;

        /**
         * Constructor
         * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
         * @param context
         */
        public LoginModel(Context context) {

            super(context, DB_NAME, null, 10);
            this.myContext = context;
            this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
          //  this.DB_PATH = myContext.getDatabasePath(DB_NAME).getPath();
            Log.e("Path 1", DB_PATH);
        }


        public void createDataBase() throws IOException {
            boolean dbExist = checkDataBase();
       //     boolean dbExist = false;
            if (dbExist) {

            } else {
                this.getReadableDatabase();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    throw new Error("Error copying database");
                }
            }
        }

        private boolean checkDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                String myPath = DB_PATH + DB_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
            } catch (SQLiteException e) {
               e.printStackTrace();
            }
            if (checkDB != null) {
                checkDB.close();
            }
            return checkDB != null ? true : false;
        }

        private void copyDataBase() throws IOException {
            InputStream myInput = myContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[10];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }


        public void openDataBase() throws SQLException {

            //Open the database
            String myPath = DB_PATH + DB_NAME;
            myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public synchronized void close() {

            if(myDataBase != null)
                myDataBase.close();

            super.close();

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

        public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
            return myDataBase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        }

        public boolean register(String name,String password){
            ContentValues values = new ContentValues();
            values.put("login", name);
            values.put("password", password);
            values.put("list",":");
            values.put("history",":");

            long a =  myDataBase.insert("Account", null, values);
            if(a!=-1){
                return true;
            }
            return false;
        }

    public boolean update(String name,String new_password){
        ContentValues values = new ContentValues();

        values.put("password", new_password);

        myDataBase.update("Account", values, "login" + " = ?",new String[] { name });

        return true;
    }

    public boolean delete(String name){

        myDataBase.delete("Account", "login" + " = ?",new String[] {name});


        return true;
    }
        // Add your public helper methods to access and get content from the database.
        // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
        // to you to create adapters for your views.


}
