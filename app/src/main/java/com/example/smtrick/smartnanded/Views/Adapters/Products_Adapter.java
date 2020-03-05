package com.example.smtrick.smartnanded.Views.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;

import java.util.List;

/**
 * Created by akshayejh on 19/12/17.
 */

public class Products_Adapter extends RecyclerView.Adapter<Products_Adapter.ViewHolder> {

    //  public List<String> fileNameList;
    public List<Products> ProductList;
    Context mContext, nContext;


    public Products_Adapter(Context fileNameList, List<Products> fileDoneList) {

        this.mContext = fileNameList;
        this.ProductList = fileDoneList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_adapter, parent, false);
        nContext = parent.getContext();
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Products products = ProductList.get(position);

        holder.txtPrice.setText("र "+products.getProductPrice());
        holder.txtDescription.setText(products.getProductDescription());
        Glide.with(mContext).load(products.getUrl()).placeholder(R.drawable.loading).into(holder.product);

    }


    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView txtPrice, txtDescription;
        public ImageView product;
        public CardView imagecard;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            product = (ImageView) mView.findViewById(R.id.imgProduct);
            imagecard = (CardView) mView.findViewById(R.id.card_view);
            txtPrice = (TextView) mView.findViewById(R.id.price);
            txtDescription = (TextView) mView.findViewById(R.id.description);
        }

    }

}