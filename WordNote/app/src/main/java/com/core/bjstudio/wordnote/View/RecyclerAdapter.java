package com.core.bjstudio.wordnote.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.core.bjstudio.wordnote.Core.Model.Note;
import com.core.bjstudio.wordnote.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<Note> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Note> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, R.mipmap.ic_launcher_round);
        holder.image.setBackground(drawable);
        holder.title.setText(note.getNoteDetail().getName());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ""+note.getNoteDetail().getId();
                Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
