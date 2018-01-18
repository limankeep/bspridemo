package com.example.springbootdemo;

import com.example.springbootdemo.domain.User;
import com.example.springbootdemo.domain.UserMapper;
import com.example.springbootdemo.domain.UserRepository;
import com.example.springbootdemo.service.UserService;

import org.aspectj.lang.annotation.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserTestCase {

	@Autowired
	private UserService userSerivce;

	@Autowired
	private UserMapper userMapper;
	
	
	@Test
	public void test() throws Exception {
		// 插入5个用户
//		userSerivce.create("a", 1);
//		userSerivce.create("b", 2);
//		userSerivce.create("c", 3);
//		userSerivce.create("d", 4);
//		userSerivce.create("e", 5);

		// 查数据库，应该有5个用户
		//Assert.assertEquals(5, userSerivce.getAllUsers().intValue());

		// 删除两个用户
		userSerivce.deleteByName("a");
		userSerivce.deleteByName("e");

		// 查数据库，应该有5个用户
		//Assert.assertEquals(3, userSerivce.getAllUsers().intValue());

	}

	
	@Test
	@Rollback
	public void findByName() throws Exception {
		userMapper.insert("AAA", 20);
		User u = userMapper.findByName("AAA");
		Assert.assertEquals(20, u.getAge().intValue());
	}

	
	
	@Test
	@Rollback
	public void testUserMapper() throws Exception {
		// insert一条数据，并select出来验证
		userMapper.insert("adadasdads", 20);
		User u = userMapper.findByName("adadasdads");
		System.out.println(u);
		Assert.assertEquals(20, u.getAge().intValue());
		// update一条数据，并select出来验证
//		u.setAge(30);
//		userMapper.update(u);
//		u = userMapper.findByName("AAA");
//		Assert.assertEquals(30, u.getAge().intValue());
//		System.out.println(u);
		
		// 删除这条数据，并select验证
//		userMapper.delete(u.getId());
//		u = userMapper.findByName("AAA");
//		Assert.assertEquals(null, u);

		u = new User("BBB", 30);
		userMapper.insertByUser(u);
		Assert.assertEquals(30, userMapper.findByName("BBB").getAge().intValue());
		System.out.println(u);

		Map<String, Object> map = new HashMap<>();
		map.put("name", "CCC");
		map.put("age", 40);
		userMapper.insertByMap(map);
		Assert.assertEquals(40, userMapper.findByName("CCC").getAge().intValue());
		System.out.println(u);

		List<User> userList = userMapper.findAll();
		for(User user : userList) {
			Assert.assertEquals(null, user.getId());
			Assert.assertNotEquals(null, user.getName());
		}
		System.out.println(userList);

	}
}