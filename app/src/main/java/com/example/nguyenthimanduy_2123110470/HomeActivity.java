package com.example.nguyenthimanduy_2123110470;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView mealListView;
    ArrayList<Meal> mealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mealListView = findViewById(R.id.mealList);

        mealList = new ArrayList<>();
        mealList.add(new Meal("Cheese Burger", R.drawable.banh1, 4.5f));
        mealList.add(new Meal("Beef Noodles", R.drawable.banh2, 4.0f));
        mealList.add(new Meal("Fried Chicken", R.drawable.banh3, 5.0f));
        mealList.add(new Meal("Burger Chicken", R.drawable.banh, 4.2f));

        MealAdapter adapter = new MealAdapter(this, mealList);
        mealListView.setAdapter(adapter);
    }
}
