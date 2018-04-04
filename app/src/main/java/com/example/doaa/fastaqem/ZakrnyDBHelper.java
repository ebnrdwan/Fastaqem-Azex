package com.example.doaa.fastaqem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by doaa on 27-Mar-18.
 */

public class ZakrnyDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase sqLiteDatabase ;

    public static final String DATABASE_NAME = "zakrny.db" ;

    public static final int DATABASE_VERSION = 1 ;

    public ZakrnyDBHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final  String SQL_CREATE_TABLE = "CREATE TABLE " + zakrnyContract.ZakrnyEntry.TABLE_NAME
                + " (" + zakrnyContract.ZakrnyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                zakrnyContract.ZakrnyEntry.COLUMNS_Text_NAME + " TEXT NOT NULL " +
                "); " ;
        db.execSQL(SQL_CREATE_TABLE);

    }


    public long addNote(String band ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues cv = new ContentValues();
        cv.put(zakrnyContract.ZakrnyEntry.COLUMNS_Text_NAME,band );
        return sqLiteDatabase.insert(zakrnyContract.ZakrnyEntry.TABLE_NAME, null, cv);
    }
    public String zekr (){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase() ;
        Cursor re = sqLiteDatabase.rawQuery("select * from zakrny",null);
        String t = re.getString(1);
        return t ;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + zakrnyContract.ZakrnyEntry.TABLE_NAME );
        onCreate(db);
    }
}

