package com.iiitb.dbservice.controller;

import com.iiitb.dbservice.model.Question;
import com.iiitb.dbservice.model.User;
import com.iiitb.dbservice.service.QuestionService;
import com.iiitb.dbservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping
public class QuestionController{

    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final UserService userService;

//    @Autowired
    private final Logger logger = LogManager.getLogger(QuestionController.class);

    @PostMapping(path="/modifyQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean modifyQuestion(@RequestBody Question question){
        logger.log(Level.INFO,"MODIFYING QUESTION :"+question.getQuestion()+" "+question.getQuestionType()+" "+question.getQID());
        return questionService.modifyQuestion(question);
    }

    @PostMapping(path="/getUserId")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer getUserId(@RequestBody String username){
        Integer ret =userService.getUserId(username);
        logger.log(Level.INFO,"USER ADDED :"+username + " with UID "+ret);
        return ret;
    }

    @PostMapping(path = "/deleteQuestion")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean deleteQuestion(@RequestBody Question question){
        logger.log(Level.INFO, "DELETING QUESTION "+ question.getQuestion()+ " WITH QID:"+question.getQID());
        return questionService.deleteQuestion(question);
    }

    @PostMapping(path="/deleteByQId")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean deleteById(@RequestBody Integer qid){
        logger.log(Level.INFO, "DELETING QUESTION WITH QID:"+ qid);
        return questionService.deleteQuestionById(qid);

    }
    @PostMapping(path = "/addQuestion")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addQuestion(@RequestBody Question question){
        Boolean ret = questionService.addQuestion(question);
        logger.log(Level.INFO,"ADDING QUESTION :"+question.getQuestion()+" "+question.getQuestionType()+" "+question.getQID());
        return ret;
    }



    @PostMapping(path = "/addUser")
    @ResponseBody
    public Boolean addUser(@RequestBody User user){
        logger.log(Level.INFO,"ADDING USER :"+user.getUsername());
        return userService.addUser(user);
    }

    @PostMapping (path = "/login" )
    @ResponseBody
//    @ResponseStatus(HttpStatus.ACCEPTED)
    public Boolean login(@RequestBody User user){
        System.out.println("User |"+ user.getUsername() + "| |" + user.getPassword());
        System.out.println("Ret "+ userService.login(user));
        logger.log(Level.INFO,"LOGGING IN USER "+user.getUsername());
        if (userService.login(user)) return true;
        return false;
    }

    @PostMapping(path = "/getQuestionsByUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Question> getQuestions(@RequestBody User user){
        List<Question> questions = userService.getQuestions(user);
        return questions;
    }


    @GetMapping(path = "/getAllQuestions")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Question> getAllQuestions(){
        List<Question> questions = questionService.getAllQuestions();
        logger.log(Level.INFO,"GETTING ALL QUESTIONS");
        return questions;
    }
}