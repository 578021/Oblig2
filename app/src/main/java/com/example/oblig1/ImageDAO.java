package com.example.oblig1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

// Creating a DAO for methods we can use in the database
@Dao
public interface ImageDAO {
    // If you upload the same image file it will only replace the old one. Same image is defined with the URI
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertImage(Image image);

    @Delete
    void delete(Image image);

    @Query("Select * from image")
    List<Image> getAll();
}
