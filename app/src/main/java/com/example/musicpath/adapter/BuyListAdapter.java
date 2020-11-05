package com.example.musicpath.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.musicpath.R;
import com.example.musicpath.model.BuyItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {
    private List<BuyItem> mList = new ArrayList<>();
    private BuyItem currentBuyItem;
    private Context mContext;

    public BuyListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv;
        public TextView countTv;
        public TextView pathTv;
        public TextView channelTv;
        View view;

        public BuyListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.textview_name_item);
            countTv = itemView.findViewById(R.id.textview_amount_item);
            pathTv = itemView.findViewById(R.id.textview_path_item);
            channelTv = itemView.findViewById(R.id.textview_channel_item);
            view = itemView;
        }
    }

    @NonNull
    @Override
    public BuyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.buy_item, parent, false);
        return new BuyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListViewHolder holder, int position) {
        currentBuyItem = mList.get(position);
        holder.nameTv.setText(currentBuyItem.getName());
        holder.countTv.setText(String.valueOf(currentBuyItem.getAmount()));
        holder.itemView.setTag(currentBuyItem.getId());
        holder.pathTv.setText(currentBuyItem.getPath());
        holder.channelTv.setText(currentBuyItem.getChannel());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,
                        "" + currentBuyItem.getId(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateAdapter(List<BuyItem> newList) {

      for (int count = 0; count < newList.size(); count++) {
            Log.e("TAG","updateAdapter: "+ newList.get(count).getName());
        }

        this.mList = newList;
        notifyDataSetChanged();
    }

    public BuyItem getCurrentBuyItem(int position) {
        return mList.get(position);
    }

}
