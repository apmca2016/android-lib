package ecs.com.app.nlibproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ecs.com.app.adapter.DataAdapter;
import ecs.com.app.model.Comment;
import ecs.com.app.rest.ApiClient;
import ecs.com.app.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiservice = ApiClient.getClient().create(ApiInterface.class);

        Call<ArrayList<Comment>> call =  apiservice.getComment();

        call.enqueue(new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                List<Comment> alist = response.body();
                recyclerView.setAdapter(new DataAdapter(alist,R.layout.list_item_show,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });


    }
}
