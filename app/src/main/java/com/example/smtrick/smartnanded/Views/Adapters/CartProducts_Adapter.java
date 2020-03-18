package com.example.smtrick.smartnanded.Views.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smtrick.smartnanded.Models.Products;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Aggriculture;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Bike;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Cars;
import com.example.smtrick.smartnanded.Views.Activities.Activity_City;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Jobs;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Market;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Mobiles;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Offers;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Others;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Properties;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Transport;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Travels;
import com.example.smtrick.smartnanded.constants.Constant;

import java.util.List;

import static com.example.smtrick.smartnanded.constants.Constant.PRODUCT_MODEL;

/**
 * Created by akshayejh on 19/12/17.
 */

public class CartProducts_Adapter extends RecyclerView.Adapter<CartProducts_Adapter.ViewHolder> {

    //  public List<String> fileNameList;
    public List<Products> ProductList;
    Context mContext, nContext;
    Activity activity;


    public CartProducts_Adapter(Context fileNameList, List<Products> fileDoneList) {

        this.mContext = fileNameList;
        this.ProductList = fileDoneList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cartproduct_adapter, parent, false);
        nContext = parent.getContext();
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Products products = ProductList.get(position);

        if (products.getProductPrice() != null && !products.getProductPrice().equalsIgnoreCase("")) {
            holder.txtPrice.setText("\u20B9 " + products.getProductPrice());
        } else {
            holder.txtPrice.setText("");
        }
        if (products.getProductDescription() != null) {
            holder.txtDescription.setText(products.getProductDescription());
        }
        if (products.getUrl() != null) {
            Glide.with(mContext).load(products.getUrl()).placeholder(R.drawable.loading).into(holder.product);
        }
        if (products.getProductQuantity() != null) {
            holder.txtQTY.setText(products.getProductQuantity());
        }

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView txtPrice, txtDescription, txtQTY;
        public ImageView product;
        public Button btnRemove;
        public CardView imagecard;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            product = (ImageView) mView.findViewById(R.id.imgProduct);
            imagecard = (CardView) mView.findViewById(R.id.card_view);
            txtPrice = (TextView) mView.findViewById(R.id.price);
            txtDescription = (TextView) mView.findViewById(R.id.description);
            txtQTY = (TextView) mView.findViewById(R.id.qtyvalue);
            btnRemove = (Button) mView.findViewById(R.id.btnRemove);
        }

    }

}
