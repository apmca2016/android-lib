package com.ecs.classes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ecs.adapter.CustomListViewAdapter;
import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class Tab2 extends Fragment {

    private ListView lv_showItem;
    private String[] placeArray = {"Bengaluru", "Mumbai", "New Delhi", "Chennai", "Pune"};
    private int[] imgArray = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3,R.drawable.bg4,R.drawable.bg5};

    public Tab2()
    {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview =  inflater.inflate(R.layout.tab2,container,false);
        lv_showItem = rootview.findViewById(R.id.lv_Places);
        CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), placeArray,imgArray);
        lv_showItem.setAdapter(adapter);
        return rootview;
    }
}
