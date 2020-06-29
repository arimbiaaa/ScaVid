package com.example.tubesarb;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void Register(View view) {
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    public void Scan(View view) {
        Intent intent = new Intent(MainActivity.this, Scan.class);
        startActivity(intent);
    }

    public void pasien(View view) {
        Intent intent = new Intent(MainActivity.this,LihatPasien.class);
        startActivity(intent);
    }

    public void info(View view){
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://covid19.go.id/"));
        startActivity(intent);
    }
}