package ecs.com.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ecs.com.app.model.Item;
import ecs.com.app.nxmllibproject.R;

/**
 * Created by ECS-27 on 02-02-2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Item> channelList;
    private int rowLayout;
    private Context context;
    int selectedPosition = -1;



    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        LinearLayout newsLayout;
        TextView newsTitle;
        TextView newslink;
        TextView newsDescription;
        TextView newslanguage;
        ImageView newsImg;


        public NewsViewHolder(View v) {
            super(v);
            context = itemView.getContext();
            newsLayout = (LinearLayout) v.findViewById(R.id.newsLayout);
            newsTitle = (TextView) v.findViewById(R.id.news_title);
            newsDescription = (TextView) v.findViewById(R.id.news_description);
            newslink = (TextView)v.findViewById(R.id.news_link);
            newslanguage = (TextView) v.findViewById(R.id.news_language);
            newsImg = (ImageView)v.findViewById(R.id.news_image);
        }

    }

    public NewsAdapter(List<Item> channelList, int rowLayout, Context context) {
        this.channelList = channelList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder,final int position) {

        holder.newsTitle.setText(channelList.get(position).getTitle());
        holder.newsDescription.setText(channelList.get(position).getDescription());
        holder.newslanguage.setText(channelList.get(position).getPubDate());
        holder.newslink.setText(channelList.get(position).getLink());
        /*holder.newsImg.setImageResource(position);*/
        if(selectedPosition==position)
            holder.newsLayout.setBackgroundColor(Color.parseColor("#acacac"));
        else
            holder.newsLayout.setBackgroundColor(Color.parseColor("#ffffff"));

        holder.newsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });



    }


    @Override
    public int getItemCount() {
        return channelList.size();
    }
}
