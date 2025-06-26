package com.example.nguyenthimanduy_2123110470;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private List<CartItem> items;
    private TextView totalView;

    public CartAdapter(Context context, List<CartItem> cartItems, TextView totalView) {
        super(context, 0, cartItems);
        this.items = cartItems;
        this.totalView = totalView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CartItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_cart_item, parent, false);
        }

        ImageView image = convertView.findViewById(R.id.cartItemImage);
        TextView name = convertView.findViewById(R.id.cartItemName);
        TextView price = convertView.findViewById(R.id.cartItemPrice);
        TextView quantity = convertView.findViewById(R.id.cartItemQuantity);
        Button btnIncrease = convertView.findViewById(R.id.btnIncrease);
        Button btnDecrease = convertView.findViewById(R.id.btnDecrease);

        image.setImageResource(item.getImageResId());
        name.setText(item.getName());
        price.setText(item.getPrice());
        quantity.setText(String.valueOf(item.getQuantity()));

        // Nút tăng
        btnIncrease.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            notifyDataSetChanged();  // gọi lại getView
            updateTotal();
        });

        // Nút giảm
        btnDecrease.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                notifyDataSetChanged();
                updateTotal();
            }
        });

        updateTotal();

        return convertView;
    }

    private void updateTotal() {
        int sum = 0;
        for (CartItem item : items) {
            try {
                int quantity = item.getQuantity();
                int price = Integer.parseInt(item.getPrice().replace(".", "").replace("đ", "").trim());
                sum += price * quantity;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        totalView.setText("Tổng: " + sum + "đ");
    }
}
