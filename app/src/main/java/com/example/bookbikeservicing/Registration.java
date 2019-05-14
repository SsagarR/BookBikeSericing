package com.example.bookbikeservicing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
   DatabaseHelper db;
    EditText et_name,et_email,et_phonenumber,et_address,et_username,et_password,et_cnfpassword;
    Spinner spinner;
    Button btn_register;
    TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        db = new DatabaseHelper(this);

        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email);
        et_phonenumber=findViewById(R.id.et_phonenumber);
        et_address=findViewById(R.id.et_address);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        et_cnfpassword=findViewById(R.id.et_cnfpassword);
        spinner=findViewById(R.id.spinner);

        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString();
                String email = et_email.getText().toString();
                String phonenumber = et_phonenumber.getText().toString();
                String address = et_address.getText().toString();
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                String confirm_password = et_cnfpassword.getText().toString();
               String spinnr= spinner.getSelectedItem().toString();




                if(password.equals(confirm_password)) {
                    long val = db.addUser(name, email, phonenumber, address, username, password,spinnr);
                    if (val > 0) {
                        Toast.makeText(Registration.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(Registration.this, Login.class);
                    }
                }
                else
                {
                    Toast.makeText(Registration.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_login=findViewById(R.id.tv_login);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent= new Intent(Registration.this,Login.class);
                startActivity(LoginIntent);
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

