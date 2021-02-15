package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Starts actvity InfoActivity
    public void infoButton(View view){
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
    }
    //Starts activity QuizActivity
    public void takeQuiz(View view){
        if(ImageDatabase.getDatabase(this).imageDAO().getAll().isEmpty()){
            Toast.makeText(this, "You need to add pictures to database, before taking quiz", Toast.LENGTH_LONG).show();
            return;
        }
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);

    }
}