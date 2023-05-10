package com.iiitb.dbservice;

import com.iiitb.dbservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<Question,Integer> {
}
