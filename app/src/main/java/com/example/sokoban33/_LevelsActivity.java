package com.example.sokoban33;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class _LevelsActivity extends ArrayAdapter<LevelEntry> {

    Context context;
    int layoutResourceId;
    List<LevelEntry> levels = null;

    public _LevelsActivity(Context context, int layoutResourceId, List<LevelEntry> levels){
        super(context, layoutResourceId, levels);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.levels = levels;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;

        if(row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new EntryHolder();
            holder.txtLevel = row.findViewById(R.id.txtLevel);
            //holder.imgLevel = row.findViewById(R.id.imgLevel);

            row.setTag(holder);
        } else {
            holder = (EntryHolder)row.getTag();
        }
    final LevelEntry entry = levels.get(position);
        holder.txtLevel.setText(entry.name);
        holder.numLevel.setText(entry.num);

        //final Context context = holder.imgLevel.getContext();
        //String imgName = entry.name;

        row.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    Intent i = new Intent(context, SokoActivity.class);
                    i.putExtra("row", entry);
                    context.startActivity(i);
                }
                return true;
            }
        });

        return row;
    }

    static class EntryHolder
    {
        TextView txtLevel;
        TextView numLevel;
        //ImageView imgLevel;
    }
}
