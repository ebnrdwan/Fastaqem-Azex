package com.example.doaa.fastaqem;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity implements AdapterMaham.ListItemClickListener {
    AdapterMaham mAdapter;
    RecyclerView recyclerView;
    SQLiteDatabase sqLiteDatabase;
    MahamDBHelper noteDbHelper = new MahamDBHelper(Main4Activity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        recyclerView = (RecyclerView) findViewById(R.id.rec);

        //getSupportActionBar().setTitle("yarab");
        //getActionBar().setDisplayHomeAsUpEnabled(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sqLiteDatabase = noteDbHelper.getWritableDatabase();
        Cursor cursor = getAllNotes();
        mAdapter = new AdapterMaham(cursor, Main4Activity.this, Main4Activity.this);
        recyclerView.setAdapter(mAdapter);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    long id = (long) viewHolder.itemView.getTag();
                    removeNote(id);
                    mAdapter.swapCursor(getAllNotes());
                }

            }).attachToRecyclerView(recyclerView);



    }

        @Override
        public void onListItemClick(int clickedItemIndex) {
            Toast.makeText(this, "item clicked", Toast.LENGTH_LONG).show();

        }


    public long addT(String n) {
        ContentValues cv = new ContentValues();
        cv.put(MahamContract.MahamEntry.COLUMNS_Text_NAME, n);
        return sqLiteDatabase.insert(MahamContract.MahamEntry.TABLE_NAME, null, cv);
    }

    //get all notes
    public Cursor getAllNotes() {
        return sqLiteDatabase.query(
                MahamContract.MahamEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MahamContract.MahamEntry._ID
        );
    }


    private boolean removeNote(long id) {
        return sqLiteDatabase.delete(MahamContract.MahamEntry.TABLE_NAME, MahamContract.MahamEntry._ID + "=" + id, null) > 0;
    }

    public void addMaham(View view) {
        Intent intent = new Intent(Main4Activity.this, Main4_1Activity.class);
        finish();
        startActivity(intent);
    }


}
