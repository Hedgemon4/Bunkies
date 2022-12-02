package com.example.bunkies.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bunkies.R;

import java.util.ArrayList;

public class PersonItemAdapter extends RecyclerView.Adapter<PersonItemAdapter.MyViewHolder>{
    private final ArrayList<PersonItem> personItems;
    private PersonClickListener personClickListener;

    public PersonItemAdapter(ArrayList<PersonItem> personItems) {
        this.personItems = personItems;
    }

    @Override
    public PersonItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonItemAdapter.MyViewHolder holder, int position) {
        String personItemText = personItems.get(position).getName();

        holder.checkBox.setText(personItemText);
        holder.checkBox.setChecked(personItems.get(position).isOnTask());
        holder.checkBox.setOnCheckedChangeListener(((compoundButton, b) -> personClickListener.onCheckClick(compoundButton, holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return personItems == null ? 0 : personItems.size();
    }

    public void setClickListener(PersonClickListener personClickListener) {
        this.personClickListener = personClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.personItemCheckbox);
        }

        @Override
        public void onClick(View view) {
            if (personClickListener != null)
                personClickListener.onCheckClick(view, getAdapterPosition());
        }
    }
}
