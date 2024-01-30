package com.app.springQuizApp.Dao;

import com.app.springQuizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by Rand() limit :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsbyCategory(String category, Integer numQ);
}
