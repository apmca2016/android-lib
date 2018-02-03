package ecs.com.app.rest;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ECS-27 on 02-02-2018.
 */

public interface ApiInterface {

    @GET("1221656.cms")
    Call<String> getChannel();

}
