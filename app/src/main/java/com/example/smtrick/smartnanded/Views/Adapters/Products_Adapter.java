package com.example.smtrick.smartnanded.Views.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class Products_Adapter extends RecyclerView.Adapter<Products_Adapter.ViewHolder> {

    //  public List<String> fileNameList;
    public List<Products> ProductList;
    Context mContext, nContext;
    String CATEGORY;
    Activity activity;


    public Products_Adapter(Context fileNameList, List<Products> fileDoneList,String category) {

        this.mContext = fileNameList;
        this.ProductList = fileDoneList;
        this.CATEGORY = category;

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

        if (products.getProductPrice() != null && !products.getProductPrice().equalsIgnoreCase("")) {
            holder.txtPrice.setText("\u20B9 " + products.getProductPrice());
        }else {
            holder.txtPrice.setText("");
        }
        holder.txtDescription.setText(products.getProductDescription());
        Glide.with(mContext).load(products.getUrl()).placeholder(R.drawable.loading).into(holder.product);

        holder.imagecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_MARKET)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Market.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_CITY)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_City.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_PROPERTIES)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Properties.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_BIKE)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Bike.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_CAR)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Cars.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_TRANSPORT)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Transport.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_TRAVELS)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Travels.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_JOBS)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Jobs.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_MOBILES)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Mobiles.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_AGRICULTURE)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Aggriculture.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_OFFERS)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Offers.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }else if (CATEGORY.equalsIgnoreCase(Constant.CATEGORY_OTHERS)){
            Intent intent = new Intent(holder.imagecard.getContext(), Activity_Others.class);
            intent.putExtra(PRODUCT_MODEL, products);
            holder.imagecard.getContext().startActivity(intent);

        }


            }
        });

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
