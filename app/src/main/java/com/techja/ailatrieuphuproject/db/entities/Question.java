package com.techja.ailatrieuphuproject.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Question")
public class Question {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    @NonNull
    public int id;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "level")
    public String level;

    @ColumnInfo(name = "casea")
    public String casea;

    @ColumnInfo(name = "caseb")
    public String caseb;

    @ColumnInfo(name = "casec")
    public String casec;

    @ColumnInfo(name = "cased")
    public String cased;

    @ColumnInfo(name = "truecase")
    public String truecase;
}
