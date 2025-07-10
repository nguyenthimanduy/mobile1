package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeActivity extends AppCompatActivity {

    ListView mealListView;
    ArrayList<Meal> mealList;
    ImageButton btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ✅ Tải giỏ hàng đã lưu
        CartManager.loadCart(this);

        mealListView = findViewById(R.id.mealList);
        btnCart = findViewById(R.id.btnCart);

        // 📦 Gọi API demo (không ảnh hưởng đến chức năng giỏ hàng)
        new Thread(() -> {
            try {
                URL url = new URL("https://fakestoreapi.com/products");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = conn.getInputStream();
                    Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
                    String response = scanner.hasNext() ? scanner.next() : "";

//                    runOnUiThread(() -> {
//                        String shortJson = response.length() > 1000 ? response.substring(0, 1000) + "..." : response;
//                        Toast.makeText(HomeActivity.this, shortJson, Toast.LENGTH_LONG).show();
//                    });

                    scanner.close();
                    inputStream.close();
                } else {
                    runOnUiThread(() ->
                            Toast.makeText(HomeActivity.this, "Lỗi phản hồi từ API: " + responseCode, Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(HomeActivity.this, "Lỗi kết nối API", Toast.LENGTH_SHORT).show());
            }
        }).start();

        // 🍔 Dữ liệu món ăn tĩnh
        mealList = new ArrayList<>();
        mealList.add(new Meal("Cheese Burger", R.drawable.banh1, 4.5f, "45.000đ", "Cheese Burger béo ngậy với lớp phô mai tan chảy, thịt bò nướng đậm vị, rau tươi và sốt đặc trưng tạo nên món ăn tuyệt vời."));
        mealList.add(new Meal("Beef Noodles", R.drawable.banh2, 4.0f, "50.000đ", "Beef Noodles với nước dùng ngọt thanh từ xương, sợi mì dai mềm, kết hợp cùng thịt bò thơm mềm và rau sống tươi xanh"));
        mealList.add(new Meal("Fried Chicken", R.drawable.banh3, 5.0f, "55.000đ", "Gà chiên giòn tan lớp vỏ bên ngoài, thịt gà mềm thơm bên trong, được tẩm ướp gia vị đậm đà và chiên vàng ruộm hấp dẫn."));
        mealList.add(new Meal("Burger Chicken", R.drawable.banh, 4.2f, "48.000đ", "Burger gà kết hợp thịt gà chiên giòn rụm, rau xà lách, cà chua tươi và nước sốt đặc biệt tạo nên hương vị khó quên."));

        // ✅ Gán adapter cho ListView
        MealAdapter adapter = new MealAdapter(this, mealList);
        mealListView.setAdapter(adapter);

        // 👉 Nút xem giỏ hàng
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
