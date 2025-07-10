package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView cartTotal;
    private Button btnCheckout;
    private ImageButton btnBackToHome;

    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ View
        cartListView = findViewById(R.id.cartListView);
        cartTotal = findViewById(R.id.cartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        // Load giỏ hàng từ bộ nhớ
        CartManager.loadCart(this);

        // Nhận dữ liệu từ ProductDetail (nếu có)
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cart_name")) {
            String name = intent.getStringExtra("cart_name");
            String price = intent.getStringExtra("cart_price");
            int image = intent.getIntExtra("cart_image", 0);

            // Tạo object Meal từ dữ liệu được truyền
            Meal meal = new Meal(name, image, 0f, price, "");
            CartManager.addToCart(this, meal);
        }

        // Lấy danh sách giỏ hàng
        cartItems = new ArrayList<>(CartManager.getCartList());

        // Gắn Adapter
        cartAdapter = new CartAdapter(this, cartItems, cartTotal);
        cartListView.setAdapter(cartAdapter);

        // Cập nhật tổng tiền
        updateTotal();

        // Thanh toán
        btnCheckout.setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
            CartManager.clearCart(this);
            cartItems.clear();
            cartAdapter.notifyDataSetChanged();
            updateTotal();
        });

        // Trở về trang chủ
        btnBackToHome.setOnClickListener(v -> {
            Intent homeIntent = new Intent(CartActivity.this, HomeActivity.class);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            finish();
        });
    }

    // Tính tổng tiền
    private void updateTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            try {
                String cleanPrice = item.getPrice().replace("đ", "").replace(".", "").trim();
                int price = Integer.parseInt(cleanPrice);
                total += price * item.getQuantity();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        cartTotal.setText("Tổng: " + total + "đ");
    }
}
