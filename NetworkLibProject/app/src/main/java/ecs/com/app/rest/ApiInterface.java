package ecs.com.app.rest;

import java.util.ArrayList;

import ecs.com.app.model.MoviesResponse;
import ecs.com.app.model.Preview;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ECS-27 on 01-02-2018.
 */

public interface ApiInterface {


    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("posts")
    Call<ArrayList<Preview>> getPreview();


}
