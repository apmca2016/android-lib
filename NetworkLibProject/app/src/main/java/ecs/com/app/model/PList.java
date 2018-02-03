package ecs.com.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ECS-27 on 01-02-2018.
 */

public class PList {
    @SerializedName("arrayList")
    @Expose
    ArrayList<Preview> arrayList;

    public ArrayList<Preview> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Preview> arrayList) {
        this.arrayList = arrayList;
    }
}
