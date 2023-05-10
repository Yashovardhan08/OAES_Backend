package com.iiitb.dbservice.service;

import com.iiitb.dbservice.model.Question;
import com.iiitb.dbservice.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class QuestionService{

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    public Boolean modifyQuestion(Question question){
//        System.out.println("ASKED TO MODIFY "+question.getQID());
        if(questionRepository.existsById(question.getQID())) {
//            deleteQuestionById(question.getQID());
            questionRepository.modify(question.getQID()
                    ,question.getQuestionType()
                    ,question.getQuestion()
                    ,question.getAnswer()
                    ,question.getVersion()
                    ,question.getCorrectOption()
                    ,question.getOption1()
                    ,question.getOption2()
                    ,question.getOption3()
                    ,question.getOption4()
                    ,question.getUser_id());
            return true;
        }
        return false;
    }

    @Autowired
    public Boolean addQuestion(Question question) {
        Question q = questionRepository.save(question);
        System.out.println("SAVED QUESTION WITH QID:"+q.getQID());
        if(q != null)return true;
        return false;
    }

    @Autowired
    public Boolean deleteQuestion(Question question){
//        questionRepository.deleteById(question.getQID());
        return deleteQuestionById(question.getQID());
    }

    public Boolean deleteQuestionById(Integer qid) {
        List<Question> ql = questionRepository.findAll();
        for ( Question q :ql){
            if(q.getQuestion()==null){
                questionRepository.delete(q);
                continue;
            }
            if(q.getQID()==qid){
                questionRepository.delete(q);
                return true;
            }
        }
//        questionRepository.deleteById(qid);
        return false;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
