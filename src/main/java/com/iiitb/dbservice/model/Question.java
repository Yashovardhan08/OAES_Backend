package com.iiitb.dbservice.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int QID;
    @Column
    private String Question;
    @Column
    private  String questionType;
    @Column
    private int version;
    @Column
    private String option1;
    @Column
    private String option2;
    @Column
    private String option3;
    @Column
    private String option4;
    @Column
    private int correctOption;
    @Column
    private String answer;
    @Column
    private Integer user_id;
}