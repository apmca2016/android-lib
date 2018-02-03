package ecs.com.app.rest;

import java.util.ArrayList;

import ecs.com.app.model.Comment;
import ecs.com.app.model.Preview;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ECS-27 on 01-02-2018.
 */

public interface ApiInterface {

    @GET("posts")
    Call<ArrayList<Preview>> getPreview();

    @GET("posts/1/comments")
    Call<ArrayList<Comment>>   getComment();
}
