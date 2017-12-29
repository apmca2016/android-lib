package com.ecs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class CustomListViewAdapter extends BaseAdapter {

    private Context context;
    private String [] placeArray;
    private int[] imgArray;

    public CustomListViewAdapter(Context tab1, String[] placeArray,int[] imgArray) {
        this.placeArray=placeArray;
        this.imgArray = imgArray;
        context=tab1;
    }

    @Override
    public int getCount() {
        return placeArray.length;
    }

    @Override
    public Object getItem(int i) {
        return placeArray[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootView=View.inflate(context,R.layout.row,null);
        final String place=placeArray[i];
        TextView textView= rootView.findViewById(R.id.textView);
        int imageposition = imgArray[i];
        ImageView imageView = rootView.findViewById(R.id.imageView);
        imageView.setImageResource(imageposition);
        textView.setText(place);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, place+" is clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
}
