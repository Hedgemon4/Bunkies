package com.example.bunkies.lists;

import android.view.View;

public interface BunkiesListClickListener {
    void onTextClick(View view, int position);

    void onItemClick(View view, int position);
}
