package com.example.bunkies.lists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bunkies.R;

import java.util.ArrayList;

public class BunkiesListAdapter extends RecyclerView.Adapter<BunkiesListAdapter.MyViewHolder> {
    private ArrayList<BunkiesList> bunkiesLists;
    private BunkiesListClickListener bunkiesListClickListener;

    public BunkiesListAdapter(ArrayList<BunkiesList> bunkiesLists) {
        this.bunkiesLists = bunkiesLists;
    }

    @Override
    public BunkiesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bunkies_list, parent, false);
        return new BunkiesListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BunkiesListAdapter.MyViewHolder holder, int position) {
        String listName = bunkiesLists.get(position).getListName();
        holder.textView.setText(listName);
        String text = "Members: ";
        String members = String.join(", ",  bunkiesLists.get(position).getPeople());
        text += members;
        holder.memberView.setText(text);
        holder.textView.setOnClickListener((i) -> bunkiesListClickListener.onTextClick(i, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return bunkiesLists == null ? 0 : bunkiesLists.size();
    }

    public void setClickListener(BunkiesListClickListener bunkiesListClickListener) {
        this.bunkiesListClickListener = bunkiesListClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final TextView memberView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.bunkiesListTextView);
            memberView = itemView.findViewById(R.id.bunkiesListDisplayMembers);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            bunkiesListClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}
