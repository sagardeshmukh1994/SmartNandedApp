package com.example.smtrick.smartnanded.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.smtrick.smartnanded.Models.Advertise;
import com.example.smtrick.smartnanded.R;
import com.example.smtrick.smartnanded.Views.Activities.Activity_Advertise_Details;

import java.util.List;


public class ImageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private Integer[] images = {R.drawable.one,R.drawable.two,R.drawable.four};
    private List<Advertise> image1;

    public ImageAdapter(Context context, List<Advertise> image) {
        this.context = context;
        this.image1 = image;
    }

    @Override
    public int getCount() {
        return image1.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
       // imageView.setImageResource(images[position]);

        Glide.with(context).load(image1.get(position).getUrl())
               .placeholder(R.drawable.loading)
                .into(imageView);

        view.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //this will log the page number that was click
//                Log.i("TAG", "This page was clicked: " + position);
                Intent intent = new Intent(context, Activity_Advertise_Details.class);
                intent.putExtra("imageurl",image1.get(position).getUrl());
                context.startActivity(intent);
            }
        });


        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }

    
}
