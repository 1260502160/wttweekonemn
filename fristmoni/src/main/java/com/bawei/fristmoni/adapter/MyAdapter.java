package com.bawei.fristmoni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.fristmoni.R;
import com.bawei.fristmoni.bean.Bean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    Bean body;
    private View view;

    public MyAdapter(Context context, Bean body) {
        this.context = context;
        this.body = body;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.rec, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(body.getResult().get(position).getCommodityName());
        holder.price.setText(body.getResult().get(position).getPrice()+"");
        GenericDraweeHierarchy gg = GenericDraweeHierarchyBuilder.newInstance(context.getResources())
                .setRoundingParams(RoundingParams.fromCornersRadius(20))
                .build();
        holder.img.setHierarchy(gg);
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(body.getResult().get(position).getMasterPic())
                .setTapToRetryEnabled(true)
                .build();
        holder.img.setController(build);

     }

    @Override
    public int getItemCount() {
        return body.getResult().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

         SimpleDraweeView img;
        TextView name;
       TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
