package com.ecs.classes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ecs.adapter.ImageAdapter;
import com.ecs.myapplication.GenrealItemActivity;
import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class Tab1 extends Fragment {


    public Tab1() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.tab1, container, false);
        GridView gridview = (GridView)rootview.findViewById(R.id.gridview);
        ImageAdapter adapter = new ImageAdapter(getContext());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
               /* Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();*/
               if(position == 0)
               {
                   Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                   startActivity(intent);
               }
                if(position == 1)
                {
                    Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                    startActivity(intent);
                }
                if(position == 2)
                {
                    Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                    startActivity(intent);
                }
                if(position == 3)
                {
                    Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                    startActivity(intent);
                }
                if(position == 4)
                {
                    Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                    startActivity(intent);
                }
                if(position == 5)
                {
                    Intent intent = new Intent(getContext(), GenrealItemActivity.class);
                    startActivity(intent);
                }

            }
        });

        return rootview;
    }
}
