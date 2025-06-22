package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnNextpage = findViewById(R.id.bLogin);
        btnNextpage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText objphone = findViewById(R.id.editTextTextEmailAddress);
                String txtphone = objphone.getText().toString();

                EditText objpass = findViewById(R.id.editTextNumberPassword);
                String txtpass = objpass.getText().toString();
                if (txtpass.equals("123") && txtphone.equals("abc")) {
                    Intent it = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(it);
                }
                else{
                    Toast.makeText(getApplicationContext(),"login fail!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        Button ccc = findViewById(R.id.bRegister);

        ccc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //use inntent
                Intent d = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(d);
            }
        });
    }
}