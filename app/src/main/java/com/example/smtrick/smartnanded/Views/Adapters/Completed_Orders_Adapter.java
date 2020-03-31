package com.example.smtrick.smartnanded.Views.Adapters;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.Order;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.callback.CallBack;
import com.example.smtrick.smartnanded.constants.Constant;
import com.example.smtrick.smartnanded.preferences.AppSharedPreference;
import com.example.smtrick.smartnanded.repository.LeedRepository;
import com.example.smtrick.smartnanded.repository.impl.LeedRepositoryImpl;
import com.example.smtrick.smartnanded.utilities.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Completed_Orders_Adapter extends RecyclerView.Adapter<Completed_Orders_Adapter.ViewHolder> {

    private Context context;
    private List<Order> uploads;
    AppSharedPreference appSharedPreference;
    LeedRepository leedRepository;
    private DatePickerDialog mDatePickerDialog;
    Order_Items_Adapter order_items_adapter;

//    Services_Adapter services_adapter;


    public Completed_Orders_Adapter(Context context, List<Order> uploads) {
        this.uploads = uploads;
        this.context = context;
    }

    public Completed_Orders_Adapter(List<Order> mUsers) {
        this.uploads = mUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.completed_orders_adapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Order order = uploads.get(position);
        String id = order.getOrdertId();
        appSharedPreference = new AppSharedPreference(holder.userCard.getContext());
        leedRepository = new LeedRepositoryImpl();

        holder.textViewName.setText(order.getUserName());
        holder.textViewMobile.setText(order.getUserMobile());
        holder.textViewAddress.setText(order.getUserAddress());
        holder.textViewDate.setText(Utility.convertMilliSecondsToFormatedDate(order.getCreatedDateTimeLong().longValue(), Constant.GLOBAL_DATE_FORMATE));

        holder.userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog1 = new Dialog(holder.userCard.getContext());
                //  dialog1.getWindow().setBackgroundDrawableResource(R.drawable.dialogboxanimation);
                dialog1.setContentView(R.layout.dialog_view_order_items);

                RecyclerView recycleOrderItems = (RecyclerView) dialog1.findViewById(R.id.recycleOrderItems);
                TextView textTotalAmount = (TextView) dialog1.findViewById(R.id.totalAmount);

                order_items_adapter = new Order_Items_Adapter(holder.userCard.getContext(),order.getOrderList());
                recycleOrderItems.setAdapter(order_items_adapter);
                recycleOrderItems.setHasFixedSize(true);
                recycleOrderItems.setLayoutManager(new LinearLayoutManager(holder.userCard.getContext()));

                  if(order.getTotalAmount() != null) {
                    textTotalAmount.setText(order.getTotalAmount());
                }else {
                    textTotalAmount.setText("null");
                }

                dialog1.show();
            }
        });
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


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.user_namevalue);
            textViewMobile = (TextView) itemView.findViewById(R.id.user_mobilevalue);
            textViewAddress = (TextView) itemView.findViewById(R.id.user_addressvalue);
            textViewDate = (TextView) itemView.findViewById(R.id.generated_datevalue);
            userCard = (CardView) itemView.findViewById(R.id.card_userid);

        }
    }

    public void reload(ArrayList<Order> leedsModelArrayList) {
        uploads.clear();
        uploads.addAll(leedsModelArrayList);
        notifyDataSetChanged();
    }
}
