package com.nitzzz.traffic.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.nitzzz.traffic.models.ReceiptModel;

import java.util.ArrayList;
import java.util.List;

public class ReceiptModule {

    private Context context;
    private SqliteManager sqliteManager;
    private Gson gson = new Gson();

    public ReceiptModule(Context context){

        this.context = context;
        sqliteManager = new SqliteManager(context);
    }

    public static final String createQuery(){

        return  " create table receipt ("+
                " receipt_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " receipt_model TEXT ) ";
    }

    public static final String dropQuery(){

        return "DROP TABLE IF EXISTS receipt";
    }

    public void insert(ReceiptModel model){

        SQLiteDatabase database = sqliteManager.getReadableDatabase();
        try {
            database.beginTransaction();

            ContentValues values = new ContentValues(1);
//            values.put("receipt_id", model.account_id);
            values.put("receipt_model", gson.toJson(model));

            database.insert("receipt", null, values);

            database.setTransactionSuccessful();

            Log.e(getClass().getSimpleName(), "inserted!");

        }catch (Exception ex){

            Log.e(getClass().getSimpleName(), ex.getMessage());
        }finally {
            try{database.endTransaction();}catch (Exception ex){}
            try{database.close();}catch (Exception ex){}
        }
    }

    public List<ReceiptModel> fetch_all(){

        String qry = "SELECT * FROM receipt";

        List<ReceiptModel> list =  new ArrayList<>();
        SQLiteDatabase database = sqliteManager.getReadableDatabase();
        Cursor cursor = database.rawQuery(qry, null);

        if (cursor.moveToFirst()) {

            do {

                String str = cursor.getString(cursor.getColumnIndex("receipt_model"));
                ReceiptModel model = gson.fromJson(str, ReceiptModel.class);

                model.id = cursor.getInt(cursor.getColumnIndex("receipt_id"));

                list.add(model);

            } while (cursor.moveToNext());
        }

        database.close();

        return list;
    }

    public List<ReceiptModel> fetch_all_reverse(){

        String qry = "SELECT * FROM receipt order by receipt_id desc";

        List<ReceiptModel> list =  new ArrayList<>();
        SQLiteDatabase database = sqliteManager.getReadableDatabase();
        Cursor cursor = database.rawQuery(qry, null);

        if (cursor.moveToFirst()) {

            do {

                String str = cursor.getString(cursor.getColumnIndex("receipt_model"));
                ReceiptModel model = gson.fromJson(str, ReceiptModel.class);

                model.id = cursor.getInt(cursor.getColumnIndex("receipt_id"));

                list.add(model);

            } while (cursor.moveToNext());
        }

        database.close();

        return list;
    }

}
