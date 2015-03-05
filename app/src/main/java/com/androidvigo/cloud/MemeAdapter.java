package com.androidvigo.cloud;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MemeAdapter extends ArrayAdapter<MemeEntity> {

    private Context mContext;
    private List<MemeEntity> mMemeEntitiesList;

    public MemeAdapter(Context context, List<MemeEntity> objects) {

        super(context, R.layout.item_meme, objects);

        mContext = context;
        mMemeEntitiesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_meme, parent, false);

        MemeEntity currentMeme = mMemeEntitiesList.get(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.item_meme_title);
        TextView emotionTextView = (TextView) convertView.findViewById(R.id.item_meme_emotion);

        titleTextView.setText(currentMeme.getTitle());
        emotionTextView.setText(currentMeme.getEmotion());

        return convertView;
    }

    @Override
    public int getCount() {

        return mMemeEntitiesList.size();
    }
}
