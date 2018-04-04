package com.example.doaa.fastaqem;

import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by doaa on 26-Mar-18.
 */

public class AdapterMaham extends RecyclerView.Adapter<AdapterMaham.MahamViewHolder> {
    Cursor cursor ;
    Context context ;
    final private AdapterMaham.ListItemClickListener mOnClickListener;


    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public AdapterMaham(Cursor cursor ,Context context, AdapterMaham.ListItemClickListener listener){
        this.cursor=cursor;
        mOnClickListener = listener ;
        this.context=context ;

    }
    //--------------------------------*three override method*-------------------------------------//
    @Override//the first method
    public AdapterMaham.MahamViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int layoutIdForListItem = R.layout.list_maham;
        LayoutInflater inflater = LayoutInflater.from(context);//new thing for our
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        AdapterMaham.MahamViewHolder viewHolder = new AdapterMaham.MahamViewHolder(view);

        return viewHolder;
    }
    //**************************-the second method*****************************//
    @Override
    public void onBindViewHolder(AdapterMaham.MahamViewHolder holder, int position) {
        if (!cursor.moveToPosition(position))
            return;
        String band= cursor.getString(cursor.getColumnIndex(MahamContract.MahamEntry.COLUMNS_Text_NAME));
        long id = cursor.getLong(cursor.getColumnIndex(MahamContract.MahamEntry._ID));
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
    class MahamViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView bandView ;


        public MahamViewHolder(View itemView) {
            super(itemView);
            bandView =(TextView)itemView.findViewById(R.id.maham);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
