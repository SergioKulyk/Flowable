package com.example.sergeykulyk.flowable;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class Presenter extends MvpPresenter<MainView> {

    private AppDatabase db;
    private UserModel userModel;

    Presenter() {
        userModel = new UserModel(1, "Micki");
    }

    public void initDb(Context context) {
        db = Room.databaseBuilder(context, AppDatabase.class, "db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        setName();
        db.userDao().insert(userModel);
    }

    @SuppressLint("CheckResult")
    public void setName() {
        getUser().subscribe(userModel -> getViewState().setName(userModel.getName()));
    }

    private Flowable<UserModel> getUser() {
        return db
                .userDao()
                .getUser(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext((Publisher<? extends UserModel>) throwable -> Log.e("User", "Error"));
    }

    public void updateName(String name) {
        userModel.setName(name);
        db.userDao().update(userModel);
    }
}
