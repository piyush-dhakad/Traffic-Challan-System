package com.nitzzz.traffic.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SqliteManager extends SQLiteOpenHelper {

    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "admin";
    private static final int DATABASE_VERSION = 1;

    public SqliteManager(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ReceiptModule.createQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ReceiptModule.dropQuery());
        onCreate(db);
    }

    /*========== for contents ==========*/

    public ArrayList<String> getTables(){

        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT name FROM sqlite_master WHERE type = 'table'",null);

        if (cursor.moveToFirst()) {
            do {
                String table = cursor.getString(0);
                list.add(table);
            } while (cursor.moveToNext());
        }

        database.close();

        return list;
    }

    public ArrayList<String[]> getTableContents(String qry){

        ArrayList<String[]> data = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(qry,null);

        data.add(cursor.getColumnNames());

        if (cursor.moveToFirst()) {
            do {
                String[] d = new String[cursor.getColumnCount()];
                for(int col=0; col<cursor.getColumnCount(); col++){
                    d[col] = cursor.getString(col);
                }
                data.add(d);

            } while (cursor.moveToNext());
        }

        database.close();

        return  data;
    }


    public boolean executeNonQuery(String qry){

        SQLiteDatabase database = getWritableDatabase();
        boolean result = false;
        try{

            database.execSQL(qry);
            result = true;

        }catch (Exception ex){

            result = false;

        }finally {

            try{database.close();}catch (Exception ex){}
        }

        return result;
    }

    public String execute(String qry){

        String result = null;

        SQLiteDatabase database = getReadableDatabase();

        try{

            Cursor cursor = database.rawQuery(qry ,null);

            if (cursor.moveToFirst()) {

                do {

                    result = cursor.getString(0);
                } while (cursor.moveToNext());
            }

        }catch (Exception ex){

            result = null;

        }finally {

            try{database.close();}catch (Exception ex){}
        }

        return result;
    }

    public List<String> executeReturnList(String qry){

        List<String> list = new ArrayList<>();

        SQLiteDatabase database = getWritableDatabase();

        try{

            Cursor cursor = database.rawQuery(qry ,null);

            if (cursor.moveToFirst()) {

                do {

                    list.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }

        }catch (Exception ex){

        }finally {

            try{database.close();}catch (Exception ex){}

        }
        return list;
    }

    public boolean executeBatch(List<String> list){

        SQLiteDatabase database = getWritableDatabase();

        boolean result = false;

        String qry = "";

        try {

            database.beginTransaction();

            for (String str : list) {

                qry = str;
                database.execSQL(str);
            }

            database.setTransactionSuccessful();
            result = true;

        }catch (Exception ex){

            Log.e(this.toString(), qry);
            result = false;

        } finally {

            try{database.endTransaction();}catch (Exception ex){}
            try{database.close();}catch (Exception ex){}
        }

        return result;
    }

    /*========== for contents ==========*/

}
