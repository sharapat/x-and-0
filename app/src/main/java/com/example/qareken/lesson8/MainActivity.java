package com.example.qareken.lesson8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlaySecondGame(View view) {
        Intent intent = new Intent(this, SecondGame.class);
        startActivity(intent);
    }
    public void PlayFirstGame(View view){
        Intent intent = new Intent(this, FirstGame.class);
        startActivity(intent);
    }
    public void Exit(View view){
        this.finish();
    }
}
