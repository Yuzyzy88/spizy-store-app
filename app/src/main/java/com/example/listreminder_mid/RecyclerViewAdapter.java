package com.example.listreminder_mid;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    //LayoutInflater inflater;
    private Context mContext ;
    private List<Product> mData;


    public RecyclerViewAdapter(Context mContext, List<Product> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.product_row, parent, false);

        // when user click container
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddTransaction.class);

                // add extended data to AddTransaction.java
                intent.putExtra("productName", mData.get(viewHolder.getAdapterPosition()).getName());
                intent.putExtra("productPrice", mData.get(viewHolder.getAdapterPosition()).getPrice());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // get data from product.java or bind data
        holder.tvProductId.setText(mData.get(position).getProductId());
        holder.tvProductName.setText(mData.get(position).getName());
        holder.tvProductPrice.setText(mData.get(position).getPrice());
        holder.tvProductType.setText(mData.get(position).getProduct_type());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductId, tvProductName, tvProductPrice, tvProductType;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            // declare  data from product_row xml
            view_container = itemView.findViewById(R.id.containerProduct);
            tvProductId = itemView.findViewById(R.id.tvProductId);
            tvProductName = itemView.findViewById(R.id.tvName);
            tvProductPrice = itemView.findViewById(R.id.tvPrice);
            tvProductType = itemView.findViewById(R.id.tvType);
        }
    }
}
