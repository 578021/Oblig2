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

    ListView myListView;
    List<Image> imageList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        myListView = findViewById(R.id.simpleListView);
        ImageDatabase db = ImageDatabase.getDatabase(this);
        imageList = db.imageDAO().getAll();
       customAdapter = new CustomAdapter(imageList);
        myListView.setAdapter(customAdapter);

        setUpListViewListener();

    }
    //A class for making rows of images and names
    public class CustomAdapter extends BaseAdapter {
        ListView myListView;
        List<Image> imageListen;


        public CustomAdapter(List<Image> imageList){
            imageListen = imageList;
        }



        @Override
        public int getCount() {
            return imageListen.size();
        }

        @Override
        public Object getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.list_row, null);

            ImageView imgView = (ImageView) view.findViewById(R.id.imageviewLL);
            TextView mTextView = (TextView) view.findViewById(R.id.textViewLL);

            imgView.setImageURI(Uri.parse(imageListen.get(position).image));
            mTextView.setText(imageListen.get(position).name);
            return view;
        }
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
    }
    public void onBackPressed(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        }
    private void setUpListViewListener() {
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
       @Override
       public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
       Context context = getApplicationContext();
       Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();
       ImageDatabase.getDatabase(context).imageDAO().delete(imageList.get(position));
       imageList.remove(position);
       customAdapter.notifyDataSetChanged();
       return true;
         }
         }
        );
    }

}