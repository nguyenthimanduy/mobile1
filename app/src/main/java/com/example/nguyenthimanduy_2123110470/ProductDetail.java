package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {

    private ImageView detailImage;
    private TextView detailName, detailPrice, detailDescription;
    private RatingBar detailRating;
    private Button btnAddToCart;
    private ImageButton btnBackToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        detailImage = findViewById(R.id.detailImage);
        detailName = findViewById(R.id.detailName);
        detailPrice = findViewById(R.id.detailPrice);
        detailDescription = findViewById(R.id.detailDescription);
        detailRating = findViewById(R.id.detailRating);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");
        int imageRes = intent.getIntExtra("imageRes", R.drawable.banh1);

        detailName.setText(name);
        detailPrice.setText("Giá: " + price);
        detailDescription.setText(description);
        detailImage.setImageResource(imageRes);

        btnAddToCart.setOnClickListener(v -> {
            Intent cartIntent = new Intent(ProductDetail.this, CartActivity.class);
            cartIntent.putExtra("cart_name", name);
            cartIntent.putExtra("cart_price", price);
            cartIntent.putExtra("cart_image", imageRes);
            startActivity(cartIntent);
        });


        btnBackToHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(ProductDetail.this, HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // xóa stack và trở về home
            startActivity(homeIntent);
            finish();
        });
    }
}
