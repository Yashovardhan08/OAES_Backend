package com.iiitb.dbservice.service;

import com.iiitb.dbservice.model.Question;
import com.iiitb.dbservice.model.User;
import com.iiitb.dbservice.repository.QuestionRepository;
import com.iiitb.dbservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final QuestionRepository questionRepository;

    @Autowired
    public Boolean addUser(User user) {
        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getUsername() == null) {
                userRepository.delete(u);

                continue;
            }

            System.out.println("User " + u.getUsername() + " " + u.getPassword());
            if (user.getUsername() == u.getUsername()) return false;
        }
        System.out.println(user.getUsername());
        userRepository.save(user);
        return true;
    }

    @Autowired
    public List<Question> getQuestions(User user) {
        List<Question> qList = questionRepository.findAll();
        List<Question> result = new ArrayList<>();
        List<User> ul = userRepository.findAll();
        System.out.println("User given :" + user.getUsername() + " " + user.getPassword());
        User fu = null;
        for (User u : ul) {
            if (u.getUsername() == null) {
                userRepository.delete(u);
                continue;
            }
            if (u.getUsername().equals(user.getUsername())) {
                fu = u;
                break;
            }
        }
        if (fu != null) System.out.println("User id=" + fu.getUser_id().toString());
        if (fu == null) return new ArrayList<>();
        for (Question q : qList) {
            if (q.getQuestion() == null) {
                questionRepository.delete(q);
                continue;
            }
//            System.out.println("Question: "+ q.getQuestion()+ " "+q.getAnswer());
            if (q.getUser_id() == fu.getUser_id()) {
                result.add(q);
            }
        }
        return result;
    }

    public boolean login(User user) {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getUsername() == null) {
                userRepository.delete(u);
                continue;
            }

            System.out.println("User " + u.getUsername() + " " + u.getPassword());
            if (user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) return true;
        }
        return false;
    }

    public Integer getUserId(String username) {
        System.out.println("Username provided" + username);
        List<User> users = userRepository.findAll();
        for (User u : users) {
            if (u.getUsername() == null) {
                userRepository.delete(u);
                continue;
            }

            System.out.println("User " + u.getUsername() + " " + u.getPassword() + " ");
            if (username.equals(u.getUsername())) {
                System.out.println("matched username");
                ;
                return u.getUser_id();
            }
        }
        return -1;
    }

}
