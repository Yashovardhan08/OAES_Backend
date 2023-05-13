package com.iiitb.dbservice.controller;


import com.iiitb.dbservice.model.JwtRequest;
import com.iiitb.dbservice.model.JwtResponse;
import com.iiitb.dbservice.model.User;
import com.iiitb.dbservice.service.QuestionService;
import com.iiitb.dbservice.service.UserService;
import com.iiitb.dbservice.utility.JWTUtility;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping
@RestController
public class AuthController {

    @Autowired
    private final QuestionService questionService;
    @Autowired
    private final UserService userService;

    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    private final Logger logger = LogManager.getLogger(QuestionController.class);

    @PostMapping("/auth")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws  Exception{
        System.out.println("User |"+ jwtRequest.getUsername() + "| |" + jwtRequest.getPassword());
        User u= new User();
        u.setPassword(jwtRequest.getPassword());u.setUsername(jwtRequest.getUsername());
        System.out.println("Ret "+ userService.login(u));
        logger.log(Level.INFO,"LOGGING IN USER "+u.getUsername());
        Boolean auth=userService.login(u);
        String token = "";
        final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        System.out.println("finished setting up userdetails");
        if(auth){
            token = jwtUtility.generateToken(userDetails);
        }
        else throw new BadCredentialsException("Invalid credentials");

        return  new JwtResponse(token);
    }
}
