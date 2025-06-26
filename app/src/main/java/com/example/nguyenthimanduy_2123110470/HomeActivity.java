package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView mealListView;
    ArrayList<Meal> mealList;
    ImageButton btnCart; // ✅ thêm khai báo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mealListView = findViewById(R.id.mealList);
        btnCart = findViewById(R.id.btnCart); // ✅ ánh xạ view giỏ hàng

        // Khởi tạo danh sách món ăn
        mealList = new ArrayList<>();
        mealList.add(new Meal("Cheese Burger", R.drawable.banh1, 4.5f, "45.000đ", "Burger kẹp phô mai béo ngậy, ngon miệng."));
        mealList.add(new Meal("Beef Noodles", R.drawable.banh2, 4.0f, "50.000đ", "Burger bò đậm đà với nước dùng thơm ngon."));
        mealList.add(new Meal("Fried Chicken", R.drawable.banh3, 5.0f, "55.000đ", "Burgergiòn rụm, hấp dẫn."));
        mealList.add(new Meal("Burger Chicken", R.drawable.banh, 4.2f, "48.000đ", "Burger gà thơm ngon với rau và sốt đặc biệt."));


        // Gán adapter cho ListView
        MealAdapter adapter = new MealAdapter(this, mealList);
        mealListView.setAdapter(adapter);


        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

    }
}
