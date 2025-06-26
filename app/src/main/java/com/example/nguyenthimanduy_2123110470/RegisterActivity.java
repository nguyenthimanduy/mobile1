package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    EditText editPhone, editPassword, editConfirmPassword, editFullName, editEmail;
    CheckBox checkOrganization;
    Button bRegister, bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        editFullName = findViewById(R.id.editFullName);
        editEmail = findViewById(R.id.editEmail);
        checkOrganization = findViewById(R.id.checkOrganization);
        bRegister = findViewById(R.id.bRegister);
        bBack = findViewById(R.id.bBack);

        // Xử lý nút Đăng ký
        bRegister.setOnClickListener(v -> {
            String phone = editPhone.getText().toString().trim();
            String password = editPassword.getText().toString();
            String confirmPassword = editConfirmPassword.getText().toString();
            String fullName = editFullName.getText().toString().trim();
            String email = editEmail.getText().toString().trim();
            boolean isMember = checkOrganization.isChecked();

            // Kiểm tra dữ liệu nhập
            if (phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                    || fullName.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Mật khẩu phải từ 6 ký tự", Toast.LENGTH_SHORT).show();
                return;
            }

            // Đăng ký thành công
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình đăng nhập
            startActivity(new Intent(this, LoginActivity.class));
        });

        // Xử lý nút Trở về
        bBack.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}
