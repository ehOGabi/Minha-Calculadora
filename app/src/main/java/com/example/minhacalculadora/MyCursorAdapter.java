package com.example.minhacalculadora;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {

    public MyCursorAdapter(Context context, Cursor cursor){
        super(context, cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView text1 = view.findViewById(android.R.id.text1);
        TextView text2 = view.findViewById(android.R.id.text2);

        String dadosLinha = "";
        for (int i = 0; i < cursor.getColumnCount(); i++){
            dadosLinha += cursor.getColumnName(1) + ": "+ cursor.getString(i)+ "\n";
        }
        text1.setText(dadosLinha);
    }
}
