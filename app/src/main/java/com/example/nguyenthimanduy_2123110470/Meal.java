package com.example.nguyenthimanduy_2123110470;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Meal {
    String name;
    int imageResId;
    float rating;

    public Meal(String name, int imageResId, float rating) {
        this.name = name;
        this.imageResId = imageResId;
        this.rating = rating;
    }
}
