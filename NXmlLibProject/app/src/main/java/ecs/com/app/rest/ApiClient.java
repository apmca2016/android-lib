package ecs.com.app.rest;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ECS-27 on 02-02-2018.
 */

public class ApiClient {

    public static final String BASE_URL = "https://timesofindia.indiatimes.com/rssfeeds/";

    /*public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";*/

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
