package com.example.doaa.fastaqem;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main5_1Activity extends AppCompatActivity {
    EditText ezakrny ;
    SQLiteDatabase sqLiteDatabase ;
    AdapterZakerny myAdapter ;
    ZakrnyDBHelper z ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_1);
        this.setFinishOnTouchOutside(false);
        ezakrny = (EditText) findViewById(R.id.e5_1) ;
        z = new ZakrnyDBHelper(this) ;
        open();

    }

    public Main5_1Activity() {
    }
    public String value (){
        return ezakrny.getText().toString() ;
    }

    public void backToAdd(View view) {
        if (ezakrny.getText().length()==0) {
            return;
        }
        String text = ezakrny.getText().toString();
        z.addNote(text);
       // addtext(text);
        //clear UI text fields
        ezakrny.clearFocus();
        ezakrny.getText().clear();
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
        Intent i= new Intent(Main5_1Activity.this,Main5Activity.class);
        startActivity(i);
      /*  SharedPreferences SharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=SharedPreferences.edit();
        editor.putString("key",Name.getText().toString());//key&value after casting it from text to string
        editor.apply();*/
    }


    public void open() throws SQLException {
        sqLiteDatabase = z.getWritableDatabase();
    }

    public long addtext(String title){
        ContentValues cv = new ContentValues();
        cv.put(zakrnyContract.ZakrnyEntry.COLUMNS_Text_NAME,title );
        return sqLiteDatabase.insert(zakrnyContract.ZakrnyEntry.TABLE_NAME,null, cv);
    }

}

