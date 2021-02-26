package com.example.oblig1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
// Creating the RoomDatabase
@Database(entities = {Image.class}, version = 1)  // Entities the object we are going to use in the database, version is the current version
public abstract class ImageDatabase extends RoomDatabase {
    public abstract ImageDAO imageDAO();
    private static ImageDatabase INSTANCE;

    static ImageDatabase getDatabase(final Context context) {
        // If the database has not been initialized, and we build a new one
        if (INSTANCE == null) {
            synchronized (ImageDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ImageDatabase.class,
                            "imagedb").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE; // if we have the imageDatabase we only return an instance of the the imageDatabase
    }
}
