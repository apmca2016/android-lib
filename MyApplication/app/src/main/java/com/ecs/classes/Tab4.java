package com.ecs.classes;


import android.os.Bundle;
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

public class Tab4 extends Fragment {

    private ListView showNature;
    private String[] natureArray = {"Green Forest", "River Side Seen", "River", "Tulip Forest", "Flower Way"};
    private int[] nImgArray = {R.drawable.ni1, R.drawable.ni2, R.drawable.ni3,R.drawable.ni4,R.drawable.ni5};

    public Tab4()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
          View rootview =  inflater.inflate(R.layout.tab4,container,false);
          showNature = (ListView)rootview.findViewById(R.id.lv_Nature);
          CustomListViewAdapter adapter = new CustomListViewAdapter(getActivity(), natureArray,nImgArray);
          showNature.setAdapter(adapter);
          /*showNature.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                  if (position == 0) {
                     *//* Intent intent = new Intent(this, QuizActivity.class);
                      startActivity(intent);*//*
                      Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                  }
                  else if (position == 1) {
                      *//*Intent intent = new Intent(this, SignUp.class);
                      startActivity(intent);*//*
                  }
                  else if (position == 2) {
                      *//*Intent intent = new Intent(this, FriendList.class);
                      startActivity(intent);*//*
                  }
              }
              
          });*/

          return rootview;
    }


}
