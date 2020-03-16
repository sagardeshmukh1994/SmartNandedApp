package com.example.smtrick.smartnanded.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smtrick.smartnanded.Models.User;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.View_User_Report_Activity;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private List<User> leedsModelArrayList;
    private Context context;


    public ReportAdapter(Context context, List<User> data) {
        this.context = context;
        this.leedsModelArrayList = data;

    }


    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cataloglist, parent, false);
        ReportAdapter.ViewHolder viewHolder = new ReportAdapter.ViewHolder(v);
        //  context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ReportAdapter.ViewHolder holder, final int position) {


        final User pveo = leedsModelArrayList.get(position);

        holder.name1.setText(pveo.getUserName());
        holder.number.setText(pveo.getMobileNumber());

        holder.layoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.layoutUser.getContext(), View_User_Report_Activity.class);
                intent.putExtra("report", pveo);
                holder.layoutUser.getContext().startActivity(intent);

            }
        });

//        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//// custom dialog
//                return true;
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return leedsModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView name1, number;
        private LinearLayout layoutUser;


        public ViewHolder(View itemView) {
            super(itemView);


            name1 = (TextView) itemView.findViewById(R.id.textpname);
            number = (TextView) itemView.findViewById(R.id.textpnumber);
            layoutUser = (LinearLayout) itemView.findViewById(R.id.card);


        }
    }

    public void reload(ArrayList<User> list) {
        list.clear();
        list.addAll(list);
        notifyDataSetChanged();
    }
}
