package ecs.com.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ecs.com.app.model.Comment;
import ecs.com.app.model.Preview;
import ecs.com.app.nlibproject.R;

/**
 * Created by ECS-27 on 01-02-2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

   private List<Preview> previewList;
    private List<Comment> commentlist;
    private int rowLayout;
    private Context context;
    int selectedPosition = -1;


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        LinearLayout datalistLayout;
        TextView dataTitle;
        TextView databody;
        //  TextView movieDescription;
        TextView rating;


        public DataViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            datalistLayout = (LinearLayout) v.findViewById(R.id.lisdata_layout);
            dataTitle = (TextView) v.findViewById(R.id.title);
            databody = (TextView) v.findViewById(R.id.body);
            // movieDescription = (TextView) v.findViewById(R.id.description);
           // rating = (TextView) v.findViewById(R.id.rating);
        }

    }

   /* public DataAdapter(List<Preview> previewList, int rowLayout, Context context) {
        this.previewList = previewList;
        this.rowLayout = rowLayout;
        this.context = context;
    }*/

    public DataAdapter(List<Comment> commentlist, int rowLayout, Context context) {
        this.commentlist = commentlist;
        this.rowLayout = rowLayout;
        this.context = context;
    }




    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {

          holder.dataTitle.setText(commentlist.get(position).getName());
          holder.databody.setText(commentlist.get(position).getEmail());
        if(selectedPosition==position)
            holder.datalistLayout.setBackgroundColor(Color.parseColor("#acacac"));
        else
            holder.datalistLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        holder.datalistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                notifyDataSetChanged();
                /*Intent intent = new Intent(context, PreviewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);*/

            }
        });
    }


    @Override
    public int getItemCount() {
        return commentlist.size();
    }




}
