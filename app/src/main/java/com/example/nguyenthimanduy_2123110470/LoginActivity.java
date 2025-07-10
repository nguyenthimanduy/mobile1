package com.example.nguyenthimanduy_2123110470;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText objphone, objpass;
    Button btnLogin, btnRegister;
    String apiUrl = "https://fakestoreapi.com/users";

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

        objphone = findViewById(R.id.editTextTextEmailAddress);
        objpass = findViewById(R.id.editTextNumberPassword);
        btnLogin = findViewById(R.id.bLogin);
        btnRegister = findViewById(R.id.bRegister);

        btnLogin.setOnClickListener(v -> {
            String txtphone = objphone.getText().toString().trim();
            String txtpass = objpass.getText().toString().trim();

            if (txtphone.isEmpty() || txtpass.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                checkLogin(txtphone, txtpass);
            }
        });

        btnRegister.setOnClickListener(v -> {
            Intent d = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(d);
        });
    }

    private void checkLogin(String emailInput, String passwordInput) {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                response -> {
                    boolean isAuthenticated = false;

                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject user = response.getJSONObject(i);
                            String email = user.getString("email");
                            String password = user.getString("password");

                            if (email.equalsIgnoreCase(emailInput) && password.equals(passwordInput)) {
                                isAuthenticated = true;
                                break;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    if (isAuthenticated) {
                        Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(this, "Sai email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(this, "Lỗi kết nối đến API", Toast.LENGTH_SHORT).show();
                    Log.e("API_ERROR", error.toString());
                });

        queue.add(jsonArrayRequest);
    }
}
//[
//        {
//        "address": {
//        "geolocation": {
//        "lat": "-37.3159",
//        "long": "81.1496"
//        },
//        "city": "kilcoole",
//        "street": "new road",
//        "number": 7682,
//        "zipcode": "12926-3874"
//        },
//        "id": 1,
//        "email": "john@gmail.com",
//        "username": "johnd",
//        "password": "m38rmF$",
//        "name": {
//        "firstname": "john",
//        "lastname": "doe"
//        },
//        "phone": "1-570-236-7033",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "-37.3159",
//        "long": "81.1496"
//        },
//        "city": "kilcoole",
//        "street": "Lovers Ln",
//        "number": 7267,
//        "zipcode": "12926-3874"
//        },
//        "id": 2,
//        "email": "morrison@gmail.com",
//        "username": "mor_2314",
//        "password": "83r5^_",
//        "name": {
//        "firstname": "david",
//        "lastname": "morrison"
//        },
//        "phone": "1-570-236-7033",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "40.3467",
//        "long": "-30.1310"
//        },
//        "city": "Cullman",
//        "street": "Frances Ct",
//        "number": 86,
//        "zipcode": "29567-1452"
//        },
//        "id": 3,
//        "email": "kevin@gmail.com",
//        "username": "kevinryan",
//        "password": "kev02937@",
//        "name": {
//        "firstname": "kevin",
//        "lastname": "ryan"
//        },
//        "phone": "1-567-094-1345",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "50.3467",
//        "long": "-20.1310"
//        },
//        "city": "San Antonio",
//        "street": "Hunters Creek Dr",
//        "number": 6454,
//        "zipcode": "98234-1734"
//        },
//        "id": 4,
//        "email": "don@gmail.com",
//        "username": "donero",
//        "password": "ewedon",
//        "name": {
//        "firstname": "don",
//        "lastname": "romer"
//        },
//        "phone": "1-765-789-6734",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "40.3467",
//        "long": "-40.1310"
//        },
//        "city": "san Antonio",
//        "street": "adams St",
//        "number": 245,
//        "zipcode": "80796-1234"
//        },
//        "id": 5,
//        "email": "derek@gmail.com",
//        "username": "derek",
//        "password": "jklg*_56",
//        "name": {
//        "firstname": "derek",
//        "lastname": "powell"
//        },
//        "phone": "1-956-001-1945",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "20.1677",
//        "long": "-10.6789"
//        },
//        "city": "el paso",
//        "street": "prospect st",
//        "number": 124,
//        "zipcode": "12346-0456"
//        },
//        "id": 6,
//        "email": "david_r@gmail.com",
//        "username": "david_r",
//        "password": "3478*#54",
//        "name": {
//        "firstname": "david",
//        "lastname": "russell"
//        },
//        "phone": "1-678-345-9856",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "10.3456",
//        "long": "20.6419"
//        },
//        "city": "fresno",
//        "street": "saddle st",
//        "number": 1342,
//        "zipcode": "96378-0245"
//        },
//        "id": 7,
//        "email": "miriam@gmail.com",
//        "username": "snyder",
//        "password": "f238&@*$",
//        "name": {
//        "firstname": "miriam",
//        "lastname": "snyder"
//        },
//        "phone": "1-123-943-0563",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "50.3456",
//        "long": "10.6419"
//        },
//        "city": "mesa",
//        "street": "vally view ln",
//        "number": 1342,
//        "zipcode": "96378-0245"
//        },
//        "id": 8,
//        "email": "william@gmail.com",
//        "username": "hopkins",
//        "password": "William56$hj",
//        "name": {
//        "firstname": "william",
//        "lastname": "hopkins"
//        },
//        "phone": "1-478-001-0890",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "40.12456",
//        "long": "20.5419"
//        },
//        "city": "miami",
//        "street": "avondale ave",
//        "number": 345,
//        "zipcode": "96378-0245"
//        },
//        "id": 9,
//        "email": "kate@gmail.com",
//        "username": "kate_h",
//        "password": "kfejk@*_",
//        "name": {
//        "firstname": "kate",
//        "lastname": "hale"
//        },
//        "phone": "1-678-456-1934",
//        "__v": 0
//        },
//        {
//        "address": {
//        "geolocation": {
//        "lat": "30.24788",
//        "long": "-20.545419"
//        },
//        "city": "fort wayne",
//        "street": "oak lawn ave",
//        "number": 526,
//        "zipcode": "10256-4532"
//        },
//        "id": 10,
//        "email": "jimmie@gmail.com",
//        "username": "jimmie_k",
//        "password": "klein*#%*",
//        "name": {
//        "firstname": "jimmie",
//        "lastname": "klein"
//        },
//        "phone": "1-104-001-4567",
//        "__v": 0
//        }
//        ]
