package com.practice.e_centrar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class gActivity extends AppCompatActivity {

    TextView txt;
    String senditem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g);

        txt = (TextView)findViewById(R.id.textView7);
        senditem = getIntent().getExtras().get("id").toString();
        Toast.makeText(gActivity.this,senditem, Toast.LENGTH_SHORT).show();

        txt.setText(senditem);
    }
}
