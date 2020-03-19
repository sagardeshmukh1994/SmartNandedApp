package com.example.smtrick.smartnanded.Views.Adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Generated_Orders_Adapter extends RecyclerView.Adapter<Generated_Orders_Adapter.ViewHolder> {

    private Context context;
    private List<Order> uploads;
    AppSharedPreference appSharedPreference;
    LeedRepository leedRepository;
    private DatePickerDialog mDatePickerDialog;

//    Services_Adapter services_adapter;


    public Generated_Orders_Adapter(Context context, List<Order> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    public Generated_Orders_Adapter(List<Order> mUsers) {
        this.uploads = mUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generated_orders_adapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Order order = uploads.get(position);
        String id = order.getOrdertId();
        appSharedPreference = new AppSharedPreference(holder.CardApprove.getContext());
        leedRepository = new LeedRepositoryImpl();

//        holder.textViewName.setText(order.getDate());
//        holder.textViewMobile.setText(order.getUserName());
//        holder.textViewPinCode.setText(order.getUserMobile());
//        holder.textViewId.setText(order.getUserPinCode());


    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewMobile;
        public TextView textViewAddress;
        public TextView textViewDate;
        public CardView userCard;

        public CardView CardApprove;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.user_namevalue);
            textViewMobile = (TextView) itemView.findViewById(R.id.user_mobilevalue);
            textViewAddress = (TextView) itemView.findViewById(R.id.user_addressvalue);
            textViewDate = (TextView) itemView.findViewById(R.id.generated_datevalue);
            userCard = (CardView) itemView.findViewById(R.id.card_userid);
            CardApprove = (CardView) itemView.findViewById(R.id.card_view_approve);

        }
    }

    public void reload(ArrayList<Order> leedsModelArrayList) {
        uploads.clear();
        uploads.addAll(leedsModelArrayList);
        notifyDataSetChanged();
    }
}
