package com.practice.e_centrar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
   Button btn;
   EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.button3);
        username= (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText3);
    }

    public void navigate(View view){

        Intent intent= new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }
}
