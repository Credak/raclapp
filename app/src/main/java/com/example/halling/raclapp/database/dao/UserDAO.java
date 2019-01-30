package com.example.halling.raclapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.halling.raclapp.database.entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllUser(List<User> user);

    @Query("DELETE FROM users")
    void deleteAllUser();

    @Query("SELECT * FROM users WHERE username = :username")
    LiveData<User> getUsername(String username);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    LiveData<User> getLogin(String username, String password);
}
