package com.example.bunkies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder> {

    private final ArrayList<ListItem> listItems;
    private ListClickListener listClickListener;

    public ListItemAdapter(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    @Override
    public ListItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemAdapter.MyViewHolder holder, int position) {
        String listItemText = listItems.get(position).getText();

        holder.checkBox.setText(listItemText);
        holder.checkBox.setChecked(listItems.get(position).isDone());
        holder.checkBox.setOnCheckedChangeListener(((compoundButton, b) -> listClickListener.onCheckClick(compoundButton, holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public void setClickListener(ListClickListener listClickListener) {
        this.listClickListener = listClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        @Override
        public void onClick(View view) {
            if (listClickListener != null)
                listClickListener.onCheckClick(view, getAdapterPosition());
        }
    }
}
