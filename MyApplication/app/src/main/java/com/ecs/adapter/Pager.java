package com.ecs.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ecs.classes.Tab1;
import com.ecs.classes.Tab2;
import com.ecs.classes.Tab3;
import com.ecs.classes.Tab4;
import com.ecs.myapplication.R;

/**
 * Created by ECS-27 on 28-12-2017.
 */

public class Pager extends FragmentPagerAdapter {

    private Context mContext;
    int tabCount;

    public Pager(FragmentManager fm, int tabCount, Context context) {
        super(fm);
        this.tabCount = tabCount;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                Tab1 tab1 = new Tab1();
                return mContext.getString(R.string.category_General);
            case 1:
                Tab2 tab2 = new Tab2();
                return mContext.getString(R.string.category_Places);
            case 2:
                Tab3 tab3 = new Tab3();
                return mContext.getString(R.string.category_Food);
            case 3:
                Tab4 tab4 = new Tab4();
                return mContext.getString(R.string.category_Nature);
            default:
                return null;
        }
    }
}


