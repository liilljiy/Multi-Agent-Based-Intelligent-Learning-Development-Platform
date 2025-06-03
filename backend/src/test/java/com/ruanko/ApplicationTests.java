package com.ruanko;

import com.ruanko.mapper.ClassMapper;
import com.ruanko.pojo.entity.User;
import com.ruanko.mapper.UserMapper;
import com.ruanko.pojo.vo.ClazzVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
		User test_user=new User();
		test_user.setUsername("sjq");
		test_user.setPassword("123");
		User user=userMapper.findByEntity(test_user);
		System.out.println("ApplicationTests.contextLoads" + "----user值= " + user);
	}


}
