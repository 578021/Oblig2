package com.example.oblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DeletePhoto extends AppCompatActivity implements View.OnClickListener{
    // initializes all the different variables that are in use in this class
    Button deleteButton;
    String namePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_photo);

         deleteButton = (Button) findViewById(R.id.DeleteButton);
    }
    //A method that deletes the image with the given text from the "database" when button is pressed
    @Override
    public void onClick(View view){
        if(view == deleteButton){
            ImageDatabase db = ImageDatabase.getDatabase(this);
            List<Image> imageList = db.imageDAO().getAll();
            EditText deleteNameText = findViewById(R.id.deleteNameText);
            namePic = deleteNameText.getText().toString();
            // Deleting the picture with the same name as the user typed in, if there is multiple with the same name it will delete the first in the list
            for (Image i : imageList){
                if(namePic.equals(i.name)){
                    db.imageDAO().delete(i); // Deleting the picture at the correct position
                    Toast.makeText(this, "Picture removed", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            // If no match is found, it will not remove anything
            Toast.makeText(this, "Picture wasn't removed, the name must be spelled correctly", Toast.LENGTH_LONG).show();
        }
    }
    // when the user press the back button, we get sent back to infoActivity and pops deletePhoto off from the activity stack
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, InfoActivity.class);
        startActivity(i);
        finish();
    }
}