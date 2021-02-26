package com.example.oblig1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;
import java.util.List;

public class UploadPhoto extends AppCompatActivity implements View.OnClickListener {

    // initializes all the different variables that are in use in this class
    private Button buttonChoose;
    private ImageView imageView;
    private Button buttonSubmit;
    Uri uri;
    private static final int PICK_IMAGE_REQUEST = 234;
    String namePic;

    // On create runs the first time the activity is opened, this initializes  the activity with the commands inside and creates an instance of it.
    // Also in the oncCreate we set the layout for the Activity
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_photo);

        // finding the correct imageView and buttons from the layout
        buttonChoose = (Button) findViewById(R.id.ChooseButton);
        buttonSubmit = (Button) findViewById(R.id.SubmitButton);
        imageView = (ImageView) findViewById(R.id.image1);

        // Making the buttons to have an onClickListener which use "this" as view
        buttonChoose.setOnClickListener(this);
        buttonSubmit.setOnClickListener(this);
    }

    //Creating the choose file popup
    private void showFileChooser(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    //Deciding what to do with the image from the previous method
    @SuppressLint("NewApi") // We need this to be able to get persistableUriPermission
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // ShowFileChooser sends and intent and PickImage_Request with startCode 234. This will "fulfill" the if statement and make it start
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            uri = data.getData();
            Uri IMGUri = Uri.parse("android.resource://com.example.oblig1/drawable24/" + R.drawable.kiwi); // This is for the test case (Image Test)

            if (!uri.toString().startsWith("android.resource:")) {
                getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION); // We need permission to be able to get the Uri
                imageView.setImageURI(uri);
            }else{
                imageView.setImageURI(IMGUri); // This is for the test case (Image Test)
            }
                   buttonSubmit.setVisibility(View.VISIBLE); // Makes the submitButton visible
        }
    }



    @Override
    public void onClick(View view){
        if(view == buttonChoose){     //Starts file chooser if the choose button was pressed
        showFileChooser(); // Let you pick an image to upload to the database
        }
        if (view == buttonSubmit) {    //Submits the image to the "database" if the submit button was pressed
            ImageDatabase db = ImageDatabase.getDatabase(this); // Getting access to the database

            EditText editTextName1 = findViewById(R.id.editTextName1);
            namePic = editTextName1.getText().toString();
            db.imageDAO().insertImage(new Image(uri.toString(), namePic )); // Inserts new image to the database with parameters Uri as a String and Name

            Toast.makeText(this, "Picture added", Toast.LENGTH_LONG).show();
        }

    }
    // when the user press the back button, we get sent back to mainActivity and pops quizActivity off from the activity stack
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
        finish();
    }
}