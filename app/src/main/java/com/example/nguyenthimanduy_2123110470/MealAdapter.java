package com.example.nguyenthimanduy_2123110470;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MealAdapter extends ArrayAdapter<Meal> {

    public MealAdapter(Context context, List<Meal> meals) {
        super(context, 0, meals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Meal meal = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_meal, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.mealImage);
        TextView name = convertView.findViewById(R.id.mealName);
        RatingBar rating = convertView.findViewById(R.id.mealRating);

        image.setImageResource(meal.imageResId);
        name.setText(meal.name);
        rating.setRating(meal.rating);

        return convertView;
    }
}
