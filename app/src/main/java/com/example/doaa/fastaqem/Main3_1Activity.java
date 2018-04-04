
package com.example.doaa.fastaqem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3_1Activity extends AppCompatActivity {
    EditText et ;
    SQLiteDatabase sqLiteDatabase ;
    MyAdapter myAdapter ;
    AwradDBHelper aw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_1);
        this.setFinishOnTouchOutside(false);
        et = (EditText) findViewById(R.id.ett) ;
        aw = new AwradDBHelper(this) ;
        open();

    }

    public void backToAdd(View view) {
        if (et.getText().length()==0) {
            return;
        }
        String text = et.getText().toString();
        addtext(text);
        //clear UI text fields
        et.clearFocus();
        et.getText().clear();
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();

        Intent i=new Intent(Main3_1Activity.this,Main3Activity.class);
        startActivity(i);

    }

    public void open() throws SQLException {
        sqLiteDatabase = aw.getWritableDatabase();
    }

    public long addtext(String title){
        ContentValues cv = new ContentValues();
        cv.put(AllContract.AwradEntry.COLUMNS_Text_NAME,title );
        return sqLiteDatabase.insert(AllContract.AwradEntry.TABLE_NAME,null, cv);
    }
}
