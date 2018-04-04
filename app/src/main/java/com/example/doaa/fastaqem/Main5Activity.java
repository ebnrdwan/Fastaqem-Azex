package com.example.doaa.fastaqem;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.TimedMetaData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.concurrent.TimeoutException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity  implements AdapterZakerny.ListItemClickListener {

    RecyclerView recyclerView ;
    AdapterZakerny adapter ;
    SQLiteDatabase sqLiteDatabase ;
    ZakrnyDBHelper noteDbHelper = new ZakrnyDBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        recyclerView=(RecyclerView)findViewById(R.id.rv5);

        //getSupportActionBar().setTitle("yarab");
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = noteDbHelper.getWritableDatabase();
        Cursor cursor = getAllZ();
        adapter = new AdapterZakerny(cursor,Main5Activity.this,Main5Activity.this);
        recyclerView.setAdapter(adapter);

 new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                long id = (long) viewHolder.itemView.getTag();

                removezekr(id);

                adapter.swapCursor(getAllZ());
            }

        }).attachToRecyclerView(recyclerView);

    }
    public long AddNewText ( String gName) {
        ContentValues cv = new ContentValues() ;
        cv.put(zakrnyContract.ZakrnyEntry.COLUMNS_Text_NAME , gName);
        return sqLiteDatabase.insert(zakrnyContract.ZakrnyEntry.TABLE_NAME , null , cv) ;
    }
    //get all notes
    public Cursor getAllZ(){
        return sqLiteDatabase.query(
                zakrnyContract.ZakrnyEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                zakrnyContract.ZakrnyEntry._ID
        );
    }
    @Override
    public void onListItemClick(int clickedItemIndex) {
        showDialog(1);


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        Calendar c = Calendar.getInstance();
        if (id == 1)
            return new TimePickerDialog(Main5Activity.this, TimeMap, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
        return null;

    }

    protected TimePickerDialog.OnTimeSetListener TimeMap =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker TimeP, int hourOfDay, int minute) {

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    calendar.set(Calendar.SECOND, 0);

                    Intent alertIntent = new Intent(getApplicationContext(), AlertReceiver.class);
                    AlarmManager alarmManager = (AlarmManager) getSystemService( ALARM_SERVICE );

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(getApplicationContext(), 0, alertIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT ));

                   //napeh.setText(hourOfDay + ":" + minute);

                }
            };



    private boolean removezekr(long id) {
        return sqLiteDatabase.delete(zakrnyContract.ZakrnyEntry.TABLE_NAME, zakrnyContract.ZakrnyEntry._ID + "=" + id, null) > 0;
    }

    public void AddZaker(View view) {
        Intent i=new Intent(Main5Activity.this,Main5_1Activity.class);
        startActivity(i);


    }

    public void back(View view) {
        Intent i= new Intent(Main5Activity.this,Main2Activity.class);
        finish();
        startActivity(i);

    }

}

