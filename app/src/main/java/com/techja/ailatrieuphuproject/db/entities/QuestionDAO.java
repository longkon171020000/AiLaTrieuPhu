package com.techja.ailatrieuphuproject.db.entities;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDAO {
    String SELECT_1_Q="SELECT * FROM Question WHERE level = :level ORDER BY RANDOM() LIMIT 1";
    @Query(SELECT_1_Q)
    Question getOneQuestionByLevel(int level);

    String SELECT_15_QUERY = "SELECT * FROM (SELECT * FROM Question WHERE level = 1 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 2 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 3 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 4 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 5 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 6 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 7 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 8 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 9 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 10 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 11 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 12 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 13 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 14 ORDER BY RANDOM() LIMIT 1)" +
            "\nUNION SELECT * FROM (SELECT * FROM Question WHERE level = 15 ORDER BY RANDOM() LIMIT 1)" +
            "\nORDER BY level ASC";
    @Query(SELECT_15_QUERY)
    List<Question> getAllQuestions();

    @Query("SELECT * FROM Question Where level = :level ORDER BY RANDOM() LIMIT 1")
    Question getQuestionByLevel(int level);


}
