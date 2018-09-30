package com.core.bjstudio.wordnote.View;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.bjstudio.wordnote.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    TextView title;
    CardView cardview;

    public ViewHolder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.image);
        title = (TextView) itemView.findViewById(R.id.title);
        cardview = (CardView) itemView.findViewById(R.id.cardview);
    }
}
