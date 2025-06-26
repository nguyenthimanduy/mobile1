package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private TextView cartTotal;
    private Button btnCheckout, btnBackToHome;

    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ view
        cartListView = findViewById(R.id.cartListView);
        cartTotal = findViewById(R.id.cartTotal);
        btnCheckout = findViewById(R.id.btnCheckout);
        btnBackToHome = findViewById(R.id.btnBackToHome);

        // Khởi tạo danh sách giỏ hàng
        cartItems = new ArrayList<>();

        // Nhận dữ liệu từ ProductDetail nếu có
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cart_name")) {
            String name = intent.getStringExtra("cart_name");
            String price = intent.getStringExtra("cart_price");
            int image = intent.getIntExtra("cart_image", 0);
            cartItems.add(new CartItem(name, price, image, 1));
        }

        // Gán adapter và cập nhật tổng tiền
        cartAdapter = new CartAdapter(this, cartItems, cartTotal);
        cartListView.setAdapter(cartAdapter);

        // Cập nhật tổng tiền sau khi gán adapter
        updateTotal();

        // Xử lý nút thanh toán
        btnCheckout.setOnClickListener(v -> {
            Toast.makeText(this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
            cartItems.clear();
            cartAdapter.notifyDataSetChanged();
            updateTotal(); // Reset tổng tiền
        });

        // Xử lý nút quay lại
        btnBackToHome.setOnClickListener(v -> {
            Intent d = new Intent(CartActivity.this, HomeActivity.class);
            d.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(d);
        });
    }

    // ✅ Hàm tính tổng tiền
    private void updateTotal() {
        int total = 0;
        for (CartItem item : cartItems) {
            try {
                // Loại bỏ "đ" và dấu chấm nếu có
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
