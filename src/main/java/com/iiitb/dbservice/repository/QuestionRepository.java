package com.iiitb.dbservice.repository;

import com.iiitb.dbservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "update question set version = :ver, question = :ques, answer = :ans, correct_option = :copt, option1 = :opt1, option2 = :opt2, option3 = :opt3, option4 = :opt4, question_type = :qtype, user_id = :uid where qid =:qid")
    public void modify(@Param("qid") Integer qid,
                @Param("qtype") String qtype,
                @Param("ques") String ques,
                @Param("ans") String ans,
                @Param("ver") Integer ver,
                @Param("copt") Integer copt,
                @Param("opt1") String op1,
                @Param("opt2") String op2,
                @Param("opt3") String op3,
                @Param("opt4") String op4,
                @Param("uid") Integer uid
                );

}