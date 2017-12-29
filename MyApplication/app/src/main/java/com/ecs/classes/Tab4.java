package com.ecs.classes;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class Tab4 extends Fragment {

    public Tab4()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          View rootview =  inflater.inflate(R.layout.tab4,container,false);
          return rootview;
    }
}
