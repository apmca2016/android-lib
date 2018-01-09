package map.demo.com.network;


import map.demo.com.pojos.GoogleSearch;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Manish on 05-01-2018.
 */

public interface Api {

    String API_SEARCH_LINK = "https://maps.googleapis.com/maps/api/place/nearbysearch/";

    //@GET("json?key=AIzaSyBFxU8QmhGDPQJwfGp4kwazHhI5MvDrqzA")
    @GET("json?")
    Call<GoogleSearch> searchNearByPlaces(@Query("location") String location, @Query("radius") int radius, @Query("keyword") String keyword, @Query("key") String key);
}
