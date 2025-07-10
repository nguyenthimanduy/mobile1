package com.example.nguyenthimanduy_2123110470;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final String PREF_NAME = "cart_pref";
    private static final String KEY_CART = "cart_items";
    private static List<CartItem> cartList = new ArrayList<>();

    // Load giỏ hàng từ SharedPreferences
    public static void loadCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CART, null);
        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<CartItem>>() {}.getType();
            cartList = gson.fromJson(json, type);
        }
    }

    // Lưu giỏ hàng
    private static void saveCart(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartList);
        editor.putString(KEY_CART, json);
        editor.apply();
    }

    public static List<CartItem> getCartList() {
        return cartList;
    }

    // Thêm món vào giỏ
    public static void addToCart(Context context, Meal meal) {
        for (CartItem item : cartList) {
            if (item.getName().equals(meal.getName())) {
                item.setQuantity(item.getQuantity() + 1);
                saveCart(context);
                return;
            }
        }
        CartItem newItem = new CartItem(meal.getName(), meal.getPrice(), meal.getImageResId(), 1);
        cartList.add(newItem);
        saveCart(context);
    }

    // Xoá món khỏi giỏ
    public static void removeItem(Context context, String itemName) {
        for (CartItem item : cartList) {
            if (item.getName().equals(itemName)) {
                cartList.remove(item);
                break;
            }
        }
        saveCart(context);
    }

    // Tăng số lượng
    public static void increaseQuantity(Context context, String itemName) {
        for (CartItem item : cartList) {
            if (item.getName().equals(itemName)) {
                item.setQuantity(item.getQuantity() + 1);
                break;
            }
        }
        saveCart(context);
    }

    // Giảm số lượng
    public static void decreaseQuantity(Context context, String itemName) {
        for (CartItem item : cartList) {
            if (item.getName().equals(itemName)) {
                int qty = item.getQuantity();
                if (qty > 1) {
                    item.setQuantity(qty - 1);
                } else {
                    cartList.remove(item); // Nếu còn 1, giảm nữa thì xóa luôn
                }
                break;
            }
        }
        saveCart(context);
    }

    // Xoá toàn bộ giỏ hàng
    public static void clearCart(Context context) {
        cartList.clear();
        saveCart(context);
    }
}
