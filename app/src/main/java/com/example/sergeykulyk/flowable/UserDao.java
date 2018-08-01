package com.example.sergeykulyk.flowable;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import io.reactivex.Flowable;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user where id = :userId")
    Flowable<UserModel> getUser(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserModel userModel);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(UserModel userModel);
}
