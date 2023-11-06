package com.example.findholidaysapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findholidaysapp.model.ListHolidaysInfo;

public class AdapterHoliday extends RecyclerView.Adapter <AdapterHoliday.ViewHolderHoliday> {

    final private ListItemClickListener onClickListener;
    public interface ListItemClickListener {
        void onListItemClick (int clickedItemIndex);
    }
    private ListHolidaysInfo listHolidaysInfo;
    private int numberItems;

    private static final String TAG = "AdapterHoliday";
    public AdapterHoliday(ListHolidaysInfo listHolidaysInfo, int length, ListItemClickListener listener) {
        this.onClickListener = listener;
        this.numberItems = length;
        this.listHolidaysInfo = listHolidaysInfo;
    }

    @NonNull
    @Override
    public ViewHolderHoliday onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holiday_list_item_layout, parent, false);
        ViewHolderHoliday viewHolderHoliday = new ViewHolderHoliday(view);
        Log.d(TAG, "onCreateViewHolder: called");
        return viewHolderHoliday;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHoliday holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class ViewHolderHoliday extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView holidayName;
        private TextView holidayDate;
        public  ViewHolderHoliday (View itemView) {
            super(itemView);
            holidayName = itemView.findViewById(R.id.holiday_name_text_view);
            holidayName.setOnClickListener(this);
            holidayDate = itemView.findViewById(R.id.holiday_date_text_view);
        }

        void bind (int position) {
            String holiday_name = listHolidaysInfo.listHolidaysInfo[position].getHoliday_name();
            String holiday_date = listHolidaysInfo.listHolidaysInfo[position].getHoliday_date();
            holidayName.setText(holiday_name);
            holidayDate.setText(holiday_date);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onClickListener.onListItemClick(position);
        }
    }

}
