package com.example.loginpagemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText edtemail,edtpassword;
TextView txtregister;
Button btnsave;
SharedPreferences sharedPreferences;

    private static final String Shared_Pref_Name="mypref";
    private static final String Key_Email="email";
    private static final String Key_Pass="name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtemail=findViewById(R.id.editTextTextEmailAddress);
        edtpassword=findViewById(R.id.editTextNumberPassword);
        btnsave=findViewById(R.id.button);
        txtregister=findViewById(R.id.textView);

        sharedPreferences=getSharedPreferences(Shared_Pref_Name,MODE_PRIVATE);
        //when opening activity first check shared preference data is available or not

        String email= sharedPreferences.getString(Key_Email,null);

        if(email!=null)
        {
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
        }

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString(Key_Email,edtemail.getText().toString());
                editor.putString(Key_Pass,edtpassword.getText().toString());
                editor.apply();

                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "Login Sucess", Toast.LENGTH_SHORT).show();
            }
        });
    }
}