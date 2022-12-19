package com.example.baocaocuoiky_vothanhhai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.baocaocuoiky_vothanhhai.model.DetailFood;


import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    private Context ct;
    private int layout;
    private ArrayList<DetailFood> arr;

    public FoodAdapter(Context ct, int layout, ArrayList<DetailFood> arr) {
        this.ct = ct;
        this.layout = layout;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size() ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            LayoutInflater i = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = i.inflate(layout,null );
        }else {

        }
        if(arr.size()>0) {
            DetailFood food = arr.get(position);
            ImageView imagefood = convertView.findViewById(R.id.image_food_item);
            TextView txvNamefood = convertView.findViewById(R.id.tv_name_food);
            TextView txvNumprice = convertView.findViewById(R.id.tvPrice);
            TextView txvNumrating = convertView.findViewById(R.id.tv_rating);

            Glide.with(ct).load(food.imageURL).into(imagefood);
            txvNamefood.setText(food.name);
            txvNumprice.setText(food.price);
            txvNumrating.setText(food.rating);
        }
            return  convertView;

    }
}
