package com.techja.ailatrieuphuproject.db.entities;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class},version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract QuestionDAO questionDAO();
}
