package com.example.oblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;

public class QuizActivity  extends AppCompatActivity {


    // initializes all the different variables that are in use in this class
    ImageView imageView;
    Button button;
    Random r;

    int pickedImage;

    TextView pointsTextView;
    TextView scoreTextView;
    int points = 0;
    int score;
    List<Image> imageList;

    // On create runs the first time the activity is opened, this initializes  the activity with the commands inside and creates an instance of it.
    // Also in the oncCreate we set the layout for the Activity



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        // finding the correct imageView and button from the layout
        imageView =(ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.answerButton);

        // Getting access to the database and finding all the pictures
        ImageDatabase db = ImageDatabase.getDatabase(this);
        imageList = (db.imageDAO().getAll());

        // this is only for the testcase (Answer Test will need this for checking the database)
        if(imageList.isEmpty()){
            imageList.add(new Image("android.resource://com.example.oblig1/drawable/" + R.drawable.kiwi, "Kiwi"));
        }

        // Setting a random picture to the quiz
        r= new Random();
        pickedImage= r.nextInt(imageList.size());
        imageView.setImageURI(Uri.parse(imageList.get(pickedImage).image));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finding the text that the user writes in, in the input field and converting it to a string
                EditText answerText =(EditText) findViewById(R.id.answerEditText1);
                String answer = answerText.getText().toString();

                // Keeping track of the points for the user
                if (answer.equals(imageList.get(pickedImage).name)){
                    pointsTextView = (TextView) findViewById(R.id.pointsTextView1);
                    points++;
                    pointsTextView.setText(Integer.toString(points));
                } else{
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
                    TextView toastl = layout.findViewById(R.id.toastTextView1);
                    String correctAnswer = imageList.get(pickedImage).name;
                    toastl.setText("Correct answer was: " + correctAnswer );
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER, 0 , 350 );
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();

                }
                // updating the total tries
                scoreTextView =(TextView) findViewById(R.id.scoreTextView1);
                score ++;
                scoreTextView.setText(Integer.toString(score));


                // picks a new random image
                pickedImage= r.nextInt(imageList.size());
                imageView.setImageURI(Uri.parse(imageList.get(pickedImage).image));

                // Resetting the textBox
                answerText.setText("");
            }

        });
    }
    // when the user press the back button, we get sent back to mainActivity and pops quizActivity off from the activity stack
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
