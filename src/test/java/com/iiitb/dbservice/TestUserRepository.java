package com.iiitb.dbservice;

import com.iiitb.dbservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestUserRepository extends JpaRepository<User,Integer> {
}
