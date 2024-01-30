package com.app.springQuizApp.Dao;

import com.app.springQuizApp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
