package com.example.bookbikeservicing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText et_username, et_password;
    Button btn_login;
    TextView tv_register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        et_username= findViewById(R.id.et_username);
        et_password= findViewById(R.id.et_password);

        btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String password= et_password.getText().toString().trim();
                Boolean res = db.checkuser(username, password);
                if(res == true) {
                    Toast.makeText(Login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Login.this, "ERROR!!! Please Login Carefully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_register=findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this,Registration.class);
                startActivity(registerIntent);
            }
        });


    }
}
