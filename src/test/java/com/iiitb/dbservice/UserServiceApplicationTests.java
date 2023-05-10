package com.iiitb.dbservice;

import com.iiitb.dbservice.model.Question;
import com.iiitb.dbservice.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceApplicationTests {

	@LocalServerPort
	private  int port;
	private  String url="http://localhost";

	private static RestTemplate restTemplate;

	@Autowired
	private TestQuestionRepository testQuestionRepository;
	@Autowired
	private TestUserRepository testUserRepository;

//	@Test
//	void contextLoads() {
//	}
	@BeforeAll
	public static  void init(){
		restTemplate=new RestTemplate();
	}
	@BeforeEach
	public void setUp(){
		url=url.concat(":").concat(port+"");
	}

	@Test
	public void testAddQuestion(){
		Question question=new Question();
		question.setQuestion("Test Question");
		question.setUser_id(1);
		question.setVersion(1);
//		question.setQID(1);
		question.setQuestionType("Subjective");
		question.setAnswer("Test answer");
		Boolean response = restTemplate.postForObject(url+"/addQuestion",question,Boolean.class);
		assertEquals(true,response);
//		assertEquals("Test Question",response.getQuestion());
//		assertEquals("Subjective",response.getQuestionType());

//		question.setQID(2);
		question.setQuestionType("Objective");
		question.setVersion(1);
		question.setOption1("option1");
		question.setOption2("option2");
		question.setOption3("option3");
		question.setOption4("option4");
		question.setCorrectOption(2);
		question.setAnswer("");
		response = restTemplate.postForObject(url+"/addQuestion",question, Boolean.class);
		assertEquals(true,response);
//		assertEquals("Test Question",response.getQuestion());
//		assertEquals("Objective",response.getQuestionType());
	}

	@Test
	public void testDeleteQuestion(){
		Question question=new Question();
		question.setQuestion("Test Question");
		question.setUser_id(1);
		question.setVersion(1);
		question.setQuestionType("Subjective");
		question.setAnswer("Test answer");
		Question sQ = testQuestionRepository.save(question);
		Boolean response = restTemplate.postForObject(url+"/deleteQuestion",sQ,Boolean.class);
		assertEquals(true,response);

		question=new Question();
		question.setQuestion("Test Question");
		question.setUser_id(1);
		question.setVersion(1);
		question.setQuestionType("Subjective");
		question.setAnswer("Test answer");
		Question savedQ=testQuestionRepository.save(question);
		response = restTemplate.postForObject(url+"/deleteByQId",savedQ.getQID(),Boolean.class);
		assertEquals(true,response);
	}
//	public void testDeleteByIdQuestion(){}
	@Test
	public void testGetAllQuestion(){
		Question question=new Question();
		question.setQuestion("Test Question");
		question.setUser_id(1);
		question.setVersion(1);
		question.setQuestionType("Subjective");
		question.setAnswer("Test answer");
		Boolean response = restTemplate.postForObject(url+"/addQuestion",question, Boolean.class);
		question.setVersion(2);
		response = restTemplate.postForObject(url+"/addQuestion",question, Boolean.class);
		question.setVersion(3);
		response = restTemplate.postForObject(url+"/addQuestion",question, Boolean.class);
		question.setVersion(4);
		response = restTemplate.postForObject(url+"/addQuestion",question, Boolean.class);
		ArrayList<Question> ql = restTemplate.getForObject(url+"/getAllQuestions",ArrayList.class );
		for(int i=0;i<ql.size();++i){
			System.out.println("ql["+i+" :"+ql.get(i));
		}
		assertEquals(5,ql.size());
	}
	@Test
	public void testAddUser(){
		User u = new User();u.setPassword("TestPass");u.setUsername("TestUser");
		Boolean response = restTemplate.postForObject(url+"/addUser",u,Boolean.class);
		assertEquals(response,true);
		testUserRepository.deleteAll();
	}
	@Test
	public void testGetUserId(){
		testUserRepository.deleteAll();
		List<User> ul = testUserRepository.findAll();
		for(User tu:ul){
			System.out.println("USER FOUND "+tu);
		}
		User u = new User();u.setPassword("TestPass");u.setUsername("TestUser");
		Boolean response = restTemplate.postForObject(url+"/addUser",u,Boolean.class);
		assertEquals(true,response);
		Integer uid = restTemplate.postForObject(url+"/getUserId","TestUser",Integer.class);
		ul = testUserRepository.findAll();
		for(User tu:ul){
			System.out.println("USER FOUND "+tu.getUsername()+" id:"+tu.getUser_id());
		}
		uid = (ul.size()==1?1:0);
		assertEquals(uid,1);
	}
	@Test
	public void testModifyQuestion(){
		Question question=new Question();
		question.setQuestion("Test Question");
		question.setUser_id(1);
		question.setVersion(1);
		question.setQuestionType("Subjective");
		question.setAnswer("Test answer");
		Question sQ = testQuestionRepository.save(question);
		sQ.setQuestion("Modified Test Question");
		Boolean response = restTemplate.postForObject(url+"/modifyQuestion",sQ,Boolean.class);
		assertEquals(true,response);
	}
	@Test
	public void testLogin(){
		User u = new User();u.setPassword("TestPass");u.setUsername("TestUser");
		Boolean response = restTemplate.postForObject(url+"/addUser",u,Boolean.class);
		assertEquals(response,true);
		response = restTemplate.postForObject(url+"/login",u,Boolean.class);
		assertEquals(true,response);
	}

}
