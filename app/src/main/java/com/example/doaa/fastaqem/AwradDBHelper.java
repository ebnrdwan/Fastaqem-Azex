package com.example.doaa.fastaqem;

/**
 * Created by doaa on 26-Mar-18.
 */


import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import static com.example.doaa.fastaqem.MahamContract.MahamEntry.COLUMNS_Text_NAME;

public class AwradDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase ;

    public static final String DATABASE_NAME = "awrad.db" ;

    public static final int DATABASE_VERSION = 1 ;

    public AwradDBHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        final  String SQL_CREATE_TABLE = "CREATE TABLE " + AllContract.AwradEntry.TABLE_NAME
                + " (" + AllContract.AwradEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                AllContract.AwradEntry.COLUMNS_Text_NAME + " TEXT NOT NULL " +
                "); " ;
        db.execSQL(SQL_CREATE_TABLE);
    }
    public long addNote(String band ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(AllContract.AwradEntry.COLUMNS_Text_NAME,band );
        return sqLiteDatabase.insert(AllContract.AwradEntry.TABLE_NAME, null, cv);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AllContract.AwradEntry.TABLE_NAME );
        onCreate(db);
    }

    public int rowsNum(){
        SQLiteDatabase db = this.getReadableDatabase();
        int rowsNum = (int) DatabaseUtils.queryNumEntries(db,AllContract.AwradEntry.TABLE_NAME);
        return rowsNum;
    }
    public String zekr (){
        SQLiteDatabase sd = this.getReadableDatabase() ;
        Cursor re = sqLiteDatabase.rawQuery("select * from zakrny",null);
        String t = re.getString(0);
        return t ;
    }
}
