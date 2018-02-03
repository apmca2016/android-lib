package ecs.com.app.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ECS-27 on 01-02-2018.
 */

public class ApiClient {

     public static final String BASE_URL="https://jsonplaceholder.typicode.com/";

     public static Retrofit retrofit = null;

     public static Retrofit getClient()
     {
         if(retrofit == null)
         {
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }
         return retrofit;
     }
}
