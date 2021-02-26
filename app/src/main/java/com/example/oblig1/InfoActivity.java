package com.example.oblig1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class InfoActivity extends Activity {

    // initializes all the different variables that are in use in this class
    ListView myListView;
    List<Image> imageList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        myListView = findViewById(R.id.simpleListView);
        ImageDatabase db = ImageDatabase.getDatabase(this); // Getting access to the database
        imageList = db.imageDAO().getAll(); // Getting all the images and creating an imageList
        customAdapter = new CustomAdapter(imageList); // Creating a new customAdapter, with all the images in the ImageList
        myListView.setAdapter(customAdapter); // Setting the myListView display with this adapter

        setUpListViewListener(); // Remove image with clicking on them

    }
    //A class for making rows of images and names
    public class CustomAdapter extends BaseAdapter {
        List<Image> imageListCustom;

        public CustomAdapter(List<Image> imageList){
            imageListCustom = imageList;
        } // Creating a list from the input parameter

        @Override
        public int getCount() {
            return imageListCustom.size();
        } // Finding the size of the list (How many pictures are present)

        @Override
        public Object getItem(int position) {
            return imageList.get(position);
        } // Retrieving the image on a certain position

        @Override
        public long getItemId(int position) {
            return 0;
        } // This is not in use, but could be used for getting the item ID


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_row, null); // Creating a List of Image and name for each Image in ImageList

            ImageView imgView = (ImageView) view.findViewById(R.id.imageviewLL);
            TextView mTextView = (TextView) view.findViewById(R.id.textViewLL);

            imgView.setImageURI(Uri.parse(imageListCustom.get(position).image)); // Getting the position of a certain image
            mTextView.setText(imageListCustom.get(position).name); // Getting the name of the image in the position
            return view;
        }
    }

    // You can press an item if you want to delete it
    private void setUpListViewListener() {
        // Check to see if there is anything within the myListView that is getting pressed
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
       public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       Context context = getApplicationContext(); // Getting the context, current state of the application (The information)
       ImageDatabase.getDatabase(context).imageDAO().delete(imageList.get(position)); // Deleting the picture in the database
       imageList.remove(position); // Removing the picture on that position
       Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();
       customAdapter.notifyDataSetChanged(); // Updating the customAdapter, And the list gets updated
       return true;
            }
          }
        );
    }
    //Start activity UploadPhoto
    public void addPicture(View view){
        Intent i = new Intent(this, UploadPhoto.class);
        startActivity(i);
        finish();
    }
    //Start activity DeletePhoto
    public void removePicture(View view){
        Intent i = new Intent(this, DeletePhoto.class);
        startActivity(i);
        finish();
    }
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}