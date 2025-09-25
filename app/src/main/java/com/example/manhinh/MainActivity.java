package com.example.manhinh;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewTitle;
    private TextView textViewForgotPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các thành phần giao diện
        initViews();

        // Thiết lập sự kiện click
        setupClickListeners();
    }

    private void initViews() {
        textViewTitle = findViewById(R.id.textViewTitle);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }

    private void setupClickListeners() {
        // Sự kiện click nút đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        // Sự kiện click nút đăng ký
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình đăng ký
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện click "Quên mật khẩu"
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chức năng quên mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Kiểm tra thông tin đăng nhập
        if (username.isEmpty()) {
            editTextUsername.setError("Vui lòng nhập tên đăng nhập");
            editTextUsername.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Vui lòng nhập mật khẩu");
            editTextPassword.requestFocus();
            return;
        }

        // Kiểm tra đăng nhập đơn giản (demo)
        if (isValidLogin(username, password)) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển sang màn hình chính
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish(); // Đóng màn hình đăng nhập
        } else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidLogin(String username, String password) {
        // Demo: chấp nhận admin/admin hoặc user/123456
        return (username.equals("admin") && password.equals("admin")) ||
                (username.equals("user") && password.equals("123456"));
    }
}