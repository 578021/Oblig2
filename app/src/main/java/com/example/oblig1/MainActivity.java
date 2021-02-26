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

    // On create runs the first time the activity is opened, this initializes  the activity with the commands inside and creates an instance of it.
    // Also in the oncCreate we set the layout for the Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Intent works as a "glue" between the activities and describes / contains an description of the operation to be preformed
    public void infoButton(View view){
        Intent i = new Intent(this, InfoActivity.class);
        // when starting a new activity the activity is pushed onto the activity stack and will come at the top of the stack.
        startActivity(i);
    }

    //Starts activity QuizActivity
    public void takeQuiz(View view){
        // We check if there is any pictures in the database, to avoid the app from crashing as it does not have any picture to add to the quiz imageView
        if(ImageDatabase.getDatabase(this).imageDAO().getAll().isEmpty()){
            Toast.makeText(this, "You need to add pictures to database, before taking quiz", Toast.LENGTH_LONG).show();
            return;
        }
        // If there picture check is fine, we open the activity.
        Intent i = new Intent(this, QuizActivity.class);
        startActivity(i);

    }

    // FinishAfiinity pops off all the activities from the activity stack so the only thing next for the back button to do is to close down the app and open app menu
    public void onBackPressed(){
        finishAffinity();
    }

}