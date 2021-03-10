package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View v1 = findViewById(R.id.button1);
        v1.setOnClickListener(this);
        View v2 = findViewById(R.id.button2);
        v2.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.button1) {
            Intent intent1 = new Intent(this, InternalStorage.class);

            this.startActivity(intent1);
        }
        if (arg0.getId() == R.id.button2) {
            Intent intent1 = new Intent(this, ExternalStorage.class);

            //start the second Activity
            this.startActivity(intent1);
        }
    }
}