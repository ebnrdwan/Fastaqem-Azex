package com.example.doaa.fastaqem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements MyAdapter.ListItemClickListener, MyAdapter.getValueListener {
    ArrayAdapter<String> ad_sp;
    String[] presentage;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SQLiteDatabase sqLiteDatabase;

    Spinner spinner;
    AwradDBHelper noteDbHelper = new AwradDBHelper(Main3Activity.this);

    TextView allSum;

    Spinner[] views = {};
    int total = 0;
    int oldPosition = 0;
    int oldValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        allSum = (TextView) findViewById(R.id.tv_showResult1);
        spinner = (Spinner) findViewById(R.id.sp);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        SharedPreferences sharedPreferences;
        if (noteDbHelper.rowsNum() == 0) {
            addBands();
        }




       /* spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });*/
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = noteDbHelper.getWritableDatabase();
        Cursor cursor = getAllNotes();
        adapter = new MyAdapter(cursor, Main3Activity.this, Main3Activity.this, this);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                long id = (long) viewHolder.itemView.getTag();
                RemoveNote(id);
                adapter.swapCursor(getAllNotes());
            }

        }).attachToRecyclerView(recyclerView);

    }

    ;


    //get all notes
    public Cursor getAllNotes() {
        return sqLiteDatabase.query(
                AllContract.AwradEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                AllContract.AwradEntry._ID
        );
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }

    public void addBands() {
        noteDbHelper.addNote("الصلاة على وقتها");
        noteDbHelper.addNote("صلاةالفجر");
        noteDbHelper.addNote("صلاة النوافل");
        noteDbHelper.addNote("صلاة الضحى");
        noteDbHelper.addNote("قيام الليل");
        noteDbHelper.addNote("صلاة الوتر");
        noteDbHelper.addNote("قرأة ورد من القرآن");
        noteDbHelper.addNote("حفظ القرآن");
        noteDbHelper.addNote("أذكار الصباح");
        noteDbHelper.addNote("أذكار المساء");
        noteDbHelper.addNote("الذكر المطلق");
        noteDbHelper.addNote("بر الوالدين");
        noteDbHelper.addNote("الدعاء");
    }


    public void AddWerd(View view) {
        Intent i = new Intent(Main3Activity.this, Main3_1Activity.class);
        finish();
        startActivity(i);

    }

    private boolean RemoveNote(long id) {
        return sqLiteDatabase.delete(AllContract.AwradEntry.TABLE_NAME, AllContract.AwradEntry._ID + "=" + id, null) > 0;
    }

    public void sum(View view) {

    }

    /*
    * set data of adapter in views */

    @Override
    public int getSumValue(int newValue, int newPosition) {
        Log.d("App_Azex_NEW_AC","value: "+ newValue+" position: "+newPosition);
        // if spinner position is still the same
        if (oldPosition == newPosition) {
            // subtract the old selected value
            total = total - oldValue ;
            // add instead of it the new value
            total= total+newValue;
            // as value already added, we consider it old value to remember it next selection
            oldValue=newValue;
        } else {
            // spinner has new position
            oldPosition = newPosition;
          // add the new value to the total values
            total = total + newValue;
            // consider the added value is old to remember it next selection
            oldValue = newValue;
        }
        Log.d("App_Azex position_AC", "old value " + oldValue + " newValue: "+newValue+" total " + total);
        Log.d("App_Azex_total_AC", String.valueOf(total));

        // add total value to the textView
        allSum.setText("" + total );
        return 0;
    }
}

