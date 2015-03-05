package com.androidvigo.cloud;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        return convertView;
    }

    @Override
    public int getCount() {

        return mMemeEntitiesList.size();
    }
}
