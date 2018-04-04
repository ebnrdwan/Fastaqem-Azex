package com.example.doaa.fastaqem;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by doaa on 26-Mar-18.
 */

public class AdapterZakerny extends RecyclerView.Adapter<AdapterZakerny.ZViewHolder> {
    Cursor cursor ;
    Context context ;
    final private ListItemClickListener mOnClickListener;



    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public AdapterZakerny(Cursor cursor ,Context context, ListItemClickListener listener){
        this.cursor=cursor;
        mOnClickListener = listener ;
        this.context=context ;

    }
    //--------------------------------*three override method*-------------------------------------//
    @Override//the first method
    public ZViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.list_zakerny;
        LayoutInflater inflater = LayoutInflater.from(context);//new thing for our
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        ZViewHolder viewHolder = new ZViewHolder(view);

        return viewHolder;
    }
    //**************************-the second method*****************************//
    @Override
    public void onBindViewHolder(ZViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))
            return;
        String band= cursor.getString(cursor.getColumnIndex(zakrnyContract.ZakrnyEntry.COLUMNS_Text_NAME));
        long id = cursor.getLong(cursor.getColumnIndex(zakrnyContract.ZakrnyEntry._ID));
        holder.bandView.setText(band);
        holder.itemView.setTag(id);

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
    class ZViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView bandView ;

        public ZViewHolder(View itemView) {
            super(itemView);
            bandView =(TextView)itemView.findViewById(R.id.zekr);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
