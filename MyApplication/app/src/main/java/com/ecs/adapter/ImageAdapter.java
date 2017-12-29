package com.ecs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 29-12-2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }


    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return mThumbIds[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }


    private Integer[] mThumbIds = {
            R.drawable.tim1, R.drawable.tim2,
            R.drawable.tim3, R.drawable.tim4,
            R.drawable.tim5, R.drawable.tim6,
            R.drawable.tim3, R.drawable.tim2,
            R.drawable.tim5, R.drawable.tim6,
            R.drawable.tim1, R.drawable.tim4,
            R.drawable.tim3, R.drawable.tim4,
            R.drawable.tim5, R.drawable.tim6,
            R.drawable.tim3, R.drawable.tim2,
            R.drawable.tim5, R.drawable.tim6,
            R.drawable.tim6 };
}
