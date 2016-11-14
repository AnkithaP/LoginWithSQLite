package com.aryaan.ankitha.loginwithsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperAdapter  {

    DbHelper helper;

    public DbHelperAdapter(Context context) {
        helper = new DbHelper(context);
    }

    public long insertData(String name,String username,String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME,name);
        contentValues.put(DbHelper.USERNAME,username);
        contentValues.put(DbHelper.PASSWORD,password);
        long id = db.insert(DbHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public String getData(String name,String password){

        //SELECT Name FROM USERTABLE WHERE UserName = ? AND Password = ?

        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.USERNAME};
        String[] selectionArgs = {name,password};
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,DbHelper.USERNAME+" = ? AND "+DbHelper.PASSWORD+" = ?",selectionArgs,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DbHelper.USERNAME);
            String username = cursor.getString(index);
            buffer.append(username);
        }
        return buffer.toString();
    }

    public String getAllData(String name){
        //SELECT * FROM USERTABLE WHERE UserName = ? AND Password = ?
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID,DbHelper.NAME,DbHelper.USERNAME,DbHelper.PASSWORD};
        String[] selectionArgs = {name};
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,DbHelper.NAME+" = ?",selectionArgs,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index0 = cursor.getColumnIndex(DbHelper.UID);
            int index1 = cursor.getColumnIndex(DbHelper.NAME);
            int index2 = cursor.getColumnIndex(DbHelper.USERNAME);
            int index3 = cursor.getColumnIndex(DbHelper.PASSWORD);

            int uid = cursor.getInt(index0);
            String uname = cursor.getString(index1);
            String user = cursor.getString(index2);
            String pass = cursor.getString(index3);

            buffer.append(uid+" "+name+" "+user+" "+pass);
        }
        return buffer.toString();
    }


    class DbHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "loginuserdatabase";
        private static final String TABLE_NAME = "USERTABLE";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String USERNAME = "UserName";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+USERNAME+" VARCHAR(255), "+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE "+TABLE_NAME+";";
        Context context;

        public DbHelper(Context context) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            Message.message(context,"Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            //CREATE TABLE USERTABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255), UserName VARCHAR(255), Password VARCHAR(255));
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context,"on create called");
            } catch (SQLException e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //DROP TABLE USERTABLE
            try {
                Message.message(context,"on upgrade called");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context,""+e);
            }

        }
    }


}
