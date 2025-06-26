package com.example.nguyenthimanduy_2123110470;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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
                    .inflate(R.layout.activity_meal, parent, false); // item layout
        }

        // Gắn view
        ImageView image = convertView.findViewById(R.id.mealImage);
        TextView name = convertView.findViewById(R.id.mealName);
        TextView price = convertView.findViewById(R.id.mealPrice);
        RatingBar rating = convertView.findViewById(R.id.mealRating);
        Button btnDetail = convertView.findViewById(R.id.btnDetail);
        Button btnAdd = convertView.findViewById(R.id.btnAdd);

        // Set dữ liệu vào view
        image.setImageResource(meal.getImageResId());
        name.setText(meal.getName());
        price.setText("Giá: " + meal.getPrice());
        rating.setRating(meal.getRating());

        // 👉 Mở trang chi tiết khi nhấn nút "Chi tiết"
        btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ProductDetail.class);
            intent.putExtra("name", meal.getName());
            intent.putExtra("price", meal.getPrice());
            intent.putExtra("imageRes", meal.getImageResId());
            intent.putExtra("rating", meal.getRating());
            intent.putExtra("description", meal.getDescription()); // 👈 THÊM DÒNG NÀY
            getContext().startActivity(intent);
        });

        // ✅ Thêm vào giỏ (chỉ hiển thị thông báo)
        btnAdd.setOnClickListener(v -> {
            Toast.makeText(getContext(), meal.getName() + " đã được thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}
