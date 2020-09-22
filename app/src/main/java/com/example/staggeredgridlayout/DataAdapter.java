package com.example.staggeredgridlayout;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.Viewholder> {
    Context mContext;
    List<Data> dataList;

    public DataAdapter(Context mContext, List<Data> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        Glide.with(mContext).load(dataList.get(position).getSmall()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alBuilder=new AlertDialog.Builder(mContext);
                ViewGroup viewGroup=(ViewGroup) view.findViewById(R.id.content);
                View v=LayoutInflater.from(mContext).inflate(R.layout.image_show, viewGroup, false);
                ImageView imageView1=v.findViewById(R.id.getImage);
                Glide.with(mContext).load(dataList.get(position).getReguler()).into(imageView1);
                alBuilder.setView(v);
                AlertDialog dialog=alBuilder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView) itemView.findViewById(R.id.imageItem);

        }
    }

}
