package com.example.bunkies.lists;

import android.view.View;

public interface ListClickListener {
    void onCheckClick(View view, int position);

    void onTextClick(View view, int position);
}
