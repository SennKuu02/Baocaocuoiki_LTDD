package com.example.baocaocuoiky_vothanhhai;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.baocaocuoiky_vothanhhai.LoginScreen.LoginActivity;
import com.example.baocaocuoiky_vothanhhai.model.DetailFood;

public class DetailActivity extends MenuActivity {

    private ImageView imgFood;
    private TextView tvName, tvRating, tvDes, tvPrice;
    private DetailFood object;
    DetailFood food = null;
    ImageView imgBack, imgEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        anhxa();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Edit();
            }
        });

        if(getIntent() != null && getIntent().getSerializableExtra("food") != null && getIntent().hasExtra("food")){
            food = (DetailFood) getIntent().getSerializableExtra("food");
            //imgFood.setImageResource(Integer.parseInt(food.imageURL));
            tvName.setText(food.name);
            tvRating.setText(food.rating);
            tvPrice.setText(food.price);
            tvDes.setText(food.description);
            Glide.with(getApplicationContext()).load(food.imageURL).into(imgFood);
        }
    }

    private void Edit() {
        Intent intent = new Intent(this, UpdateMenuFood.class);
        startActivity(intent);
    }


    private void back() {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);

    }

    private void anhxa() {
        imgFood= findViewById(R.id.img_food);
        tvName = findViewById(R.id.tv_name);
        tvDes= findViewById(R.id.tv_description);
        tvPrice =findViewById(R.id.tc_price);
        tvRating=findViewById(R.id.tv_rating1);
        imgBack= findViewById(R.id.img_back);
        imgEdit= findViewById(R.id.imgEdit);
    }
}