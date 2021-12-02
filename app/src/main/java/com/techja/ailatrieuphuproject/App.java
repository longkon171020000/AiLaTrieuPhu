package com.techja.ailatrieuphuproject;

import android.app.Application;

import androidx.room.Room;

import com.techja.ailatrieuphuproject.db.entities.AppDB;

public class App extends Application {
    private static App INSTANCE;
    private static Storage storage;
    private AppDB db;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        storage = new Storage();
        db= Room.databaseBuilder(getApplicationContext(),AppDB.class,"Quesiton")
                .createFromAsset("database/Question")
                .build();
    }

    public AppDB getDb() {
        return db;
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public static Storage getStorage() {
        return storage;
    }
}
