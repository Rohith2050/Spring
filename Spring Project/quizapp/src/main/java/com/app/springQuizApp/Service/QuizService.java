package com.app.springQuizApp.Service;

import com.app.springQuizApp.Dao.QuestionDao;
import com.app.springQuizApp.Dao.QuizDao;
import com.app.springQuizApp.Model.Question;
import com.app.springQuizApp.Model.QuestionWrapper;
import com.app.springQuizApp.Model.Quiz;
import com.app.springQuizApp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        List<Question> questions=questionDao.findRandomQuestionsbyCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new  ResponseEntity<>("success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizAnswers(Integer id) {
        Optional<Quiz> quiz=quizDao.findById(id);
        List<Question> questionsDB=quiz.get().getQuestions();
        List<QuestionWrapper> QW= new ArrayList<>();
        for(Question q: questionsDB){
            QuestionWrapper qw1=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            QW.add(qw1);
        }
        return new ResponseEntity<>(QW,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Response r:responses){
            if (r.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }

}
