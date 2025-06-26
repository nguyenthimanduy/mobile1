package com.example.nguyenthimanduy_2123110470;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class Meal {
    private String name;
    private int imageResId;
    private float rating;
    private String price;
    private String description;

    public Meal(String name, int imageResId, float rating, String price,String description) {
        this.name = name;
        this.imageResId = imageResId;
        this.rating = rating;
        this.price = price;
        this.description = description;

    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public float getRating() { return rating; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }
}

