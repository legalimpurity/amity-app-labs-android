package com.example.amityapplabs.amityincubatorlistproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private String[] mTitleDataset;
    private String[] mSubTitleDataset;

    MyAdapter()
    {
        mTitleDataset = new String[35];
        mSubTitleDataset = new String[35];
        for(int i =0; i< 35; i++)
        {
            mTitleDataset[i] = "Amity"+i;
            mSubTitleDataset[i] = "Incubator"+i;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applab_student,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTitleTextView.setText(mTitleDataset[position]);
        holder.mSubTitleTextView.setText(mSubTitleDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mTitleDataset.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mTitleTextView;
        private TextView mSubTitleTextView;
        public MyViewHolder(View v)
        {
            super(v);
            mTitleTextView = v.findViewById(R.id.title_text_view);
            mSubTitleTextView = v.findViewById(R.id.subtitle_text_view);
        }
    }

}
