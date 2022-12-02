package com.example.bunkies.messaging;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bunkies.R;

import java.util.List;

public class contactAdapter extends RecyclerView.Adapter {

    private List<Contact> mContacts;
    
    public contactAdapter(List<Contact> contacts) {
        mContacts = contacts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact,parent,false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView textView = holder.itemView.findViewById(R.id.contact_name);
        textView.setText(contact.getName());
        Button button = holder.itemView.findViewById(R.id.message_button);
        button.setText("Message");
        button.setEnabled(contact.isOnline());


    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public Button messageButton;



        public ViewHolder(View itemView){
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent message = new Intent(view.getContext(),MessageListActivity.class);
                    message.putExtra("chatName", nameTextView.getText());
                    view.getContext().startActivity(message);
                }
            });

        }

        @Override
        public void onClick(View view) {

        }
    }

}
