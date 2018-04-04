package com.example.doaa.fastaqem;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4_1Activity extends AppCompatActivity {
    EditText emaham ;
    SQLiteDatabase sqLiteDatabase ;
    AdapterMaham myAdapter ;
    MahamDBHelper m ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_1);
        this.setFinishOnTouchOutside(false);
        emaham = (EditText) findViewById(R.id.ed) ;
        m = new MahamDBHelper(this) ;
        open();

    }



    public void open() throws SQLException {
        sqLiteDatabase = m.getWritableDatabase();
    }

    public long addtext(String title){
        ContentValues cv = new ContentValues();
        cv.put(MahamContract.MahamEntry.COLUMNS_Text_NAME,title );
        return sqLiteDatabase.insert(MahamContract.MahamEntry.TABLE_NAME,null, cv);
    }

    public void backmaham(View view) {
        if (emaham.getText().length()==0) {
            return;
        }
        String t = emaham.getText().toString();
        addtext(t);
        //clear UI text fields
        emaham.clearFocus();
        emaham.getText().clear();
        Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(Main4_1Activity.this,Main4Activity.class);
        startActivity(i);

    }
    }


