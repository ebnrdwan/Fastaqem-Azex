package com.example.doaa.fastaqem;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by doaa on 24-Mar-18.
 */

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.GahedViewHolder> {
    Cursor cursor ;
    Context context ;
    final private ListItemClickListener mOnClickListener;
    MyAdapter.getValueListener getValueListener ;
    private int myposition;
    private int total_value=0;
//GahedViewHolder gh=new GahedViewHolder()

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public MyAdapter(Cursor cursor ,Context context, ListItemClickListener listener, getValueListener listenervalue){
        getValueListener = listenervalue;
        this.cursor=cursor;
        mOnClickListener = listener ;
        this.context=context ;
    }
 


    //--------------------------------*three override method*-------------------------------------//
    @Override//the first method
    public GahedViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.list_awrad;
        LayoutInflater inflater = LayoutInflater.from(context);//new thing for our
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        GahedViewHolder viewHolder = new GahedViewHolder(view);

        return viewHolder;
    }
//**************************-the second method*****************************//
    @Override
    public void onBindViewHolder(GahedViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))
            return;
        String band= cursor.getString(cursor.getColumnIndex(AllContract.AwradEntry.COLUMNS_Text_NAME));
        long id = cursor.getLong(cursor.getColumnIndex(AllContract.AwradEntry._ID));
        holder.bandView.setText(band);

        if (myposition==position){

//            Log.d("App_azex_adapter",total_value+"");
//            getValueListener.getSumValue(total_value,position);

        }
        else {
//            Log.d("App_azex_adapter",total_value+"");

        }


    }
//***********************-the therd method****************************//
    @Override
    public int getItemCount() { //it return the num of item of recyclerview
        return cursor.getCount();
    }
   public void swapCursor(Cursor newCursor) {
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }}

//---------------------------------*the inner class*-----------------------------------------------------//
class GahedViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {


    TextView bandView ;
   // todo  how to add action
    private Spinner sp;
    int spinnerValue = 0 ;
    public GahedViewHolder(View itemView) {
        super(itemView);
        bandView =(TextView)itemView.findViewById(R.id.band );
        itemView.setOnClickListener(this);
        sp = (Spinner) itemView.findViewById(R.id.sp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myonItemSelected(adapterView,view,i,l);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void myonItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sp1= String.valueOf(parent.getSelectedItem());
           Log.d("App_azex_adapter",String.valueOf(parent.getSelectedItem()));
        myposition=position;

        Log.d("App_azex_adapter ",spinnerValue+"");
        if(sp1.contentEquals("لم تؤدى")) {
            spinnerValue =0 ;
            total_value=+spinnerValue;
            getValueListener.getSumValue(spinnerValue,getAdapterPosition());
            Toast.makeText(context,String.valueOf(total_value),Toast.LENGTH_SHORT).show();
            Log.d("App_azex_adapter",spinnerValue+""+total_value);

        }
        else if (sp1.contentEquals("من 20 : 40")){
            spinnerValue = 1;
            total_value=+spinnerValue;
            getValueListener.getSumValue(spinnerValue,getAdapterPosition());
            Log.d("App_azex_adapter",spinnerValue+""+total_value);
        }
        else if (sp1.contentEquals("من 40 : 60")){
            spinnerValue = 2;
            total_value=+spinnerValue;
            getValueListener.getSumValue(spinnerValue,getAdapterPosition());
            Log.d("App_azex_adapter",spinnerValue+""+total_value);

        }
        else if (sp1.contentEquals("من 60 : 80")){
            spinnerValue = 3;
            total_value=+spinnerValue;
            Log.d("App_azex_adapter",spinnerValue+""+total_value);
            getValueListener.getSumValue(spinnerValue,getAdapterPosition());

        }
        else if (sp1.contentEquals("من 80 : 100")){
            spinnerValue = 4;
            total_value=+spinnerValue;
            Log.d("App_azex_adapter",spinnerValue+""+total_value);
            getValueListener.getSumValue(spinnerValue,getAdapterPosition());

        }
    }
    public int getSpinnerValue() {
        return spinnerValue;
    }

    public void setSpinnerValue(int spinnerValue) {
        this.spinnerValue = spinnerValue;
    }


    @Override
    public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
           mOnClickListener.onListItemClick(clickedPosition);
        }
    }

    public interface getValueListener{
        int getSumValue(int value, int position);
    }


}
