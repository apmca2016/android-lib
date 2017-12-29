package com.ecs.classes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class Tab3 extends Fragment {

    public Tab3()
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview =  inflater.inflate(R.layout.tab3,container,false);
        return rootview;
    }
}
