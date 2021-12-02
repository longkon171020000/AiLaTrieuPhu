package com.techja.ailatrieuphuproject;

import com.techja.ailatrieuphuproject.db.entities.Question;

import java.util.List;

public final class Storage {
    public String question,casea,caseb,casec,cased,truecase;
    public List<Question> questionList;

    @Override
    public String toString() {
        return "Storage{" +
                "questionList=" + questionList +
                '}';
    }
}
