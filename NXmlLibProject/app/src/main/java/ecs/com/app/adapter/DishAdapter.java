package ecs.com.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;

import ecs.com.app.model.DishDetails;
import ecs.com.app.nxmllibproject.R;

/**
 * Created by ECS-27 on 03-02-2018.
 */

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {


    private ArrayList<DishDetails> dishlist;
    private Context context;


    public DishAdapter(ArrayList<DishDetails> dishlist, Context context) {
        this.dishlist = dishlist;
        this.context = context;
    }

    public class DishViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        ImageView img;
        SimpleRatingBar ratingBar;

        public DishViewHolder(View itemView) {
            super(itemView);

            nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
            img= (ImageView) itemView.findViewById(R.id.dishImage);
            ratingBar= (SimpleRatingBar) itemView.findViewById(R.id.ratingbar);

        }
    }



    @Override
    public DishViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.model_layout,parent,false);
        return new DishViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        DishDetails s=dishlist.get(position);
        holder.nameTxt.setText(s.getName());
        holder.img.setImageResource(s.getImage());
        holder.ratingBar.setRating(s.getRating());
    }

    @Override
    public int getItemCount() {
        return dishlist.size();
    }


}
