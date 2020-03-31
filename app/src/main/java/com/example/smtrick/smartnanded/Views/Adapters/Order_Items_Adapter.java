package com.example.smtrick.smartnanded.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.View_User_Report_Activity;

import java.util.ArrayList;
import java.util.List;

public class Order_Items_Adapter extends RecyclerView.Adapter<Order_Items_Adapter.ViewHolder> {

    private List<String> leedsModelArrayList;
    private Context context;


    public Order_Items_Adapter(Context context, List<String> data) {
        this.context = context;
        this.leedsModelArrayList = data;

    }


    @Override
    public Order_Items_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderlist, parent, false);
        Order_Items_Adapter.ViewHolder viewHolder = new Order_Items_Adapter.ViewHolder(v);
        //  context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Order_Items_Adapter.ViewHolder holder, final int position) {


        final String pveo = leedsModelArrayList.get(position);

        holder.name1.setText(pveo);

    }

    @Override
    public int getItemCount() {
        return leedsModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name1;


        public ViewHolder(View itemView) {
            super(itemView);


            name1 = (TextView) itemView.findViewById(R.id.textpname);


        }
    }

    public void reload(ArrayList<User> list) {
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }
}
