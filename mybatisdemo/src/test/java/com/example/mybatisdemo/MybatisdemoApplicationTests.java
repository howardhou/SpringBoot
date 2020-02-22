package com.example.mybatisdemo;

import com.example.mybatisdemo.dao.*;
import com.example.mybatisdemo.jdbc.JdbcDao;
import com.example.mybatisdemo.jpa.UserJpa;
import com.example.mybatisdemo.entity.Asset;
import com.example.mybatisdemo.entity.Card;
import com.example.mybatisdemo.entity.User;
import com.example.mybatisdemo.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisdemoApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		try {
			// 1. 测试 JDBC
			JdbcDao.getAll();

			// 2. 测试 JDBCTemplate
			List<User> list = userService.getAllUser();
			System.out.println("JDBCTemplate, userService.getAllUser(): " + list);

			// 3. 测试 MyBatis
			User user = userService.getUserById(1);
			System.out.println("MyBatis , userService.getUserById(1): " + user);

			// MyBatis 一对一 测试
			testGetUser(1);
			testGetCard(1);

			//MyBatis 一对多测试
			testGetUserWithAsset(1);

			//MyBatis 多对一测试
			testGetAssetById(1);

			//testSaveUser();

			// 4. 测试JPS
			UserJpa dao = new UserJpa();
			//dao.saveUser();

			dao.getUser(2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//保存
	@Test
	public void testSaveUser() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		User user = new User("houdongdong", "男", 36);
		//session.insert("com.example.mybatisdemo.dao.UserMapper.save", user);

		UserDao userDao = session.getMapper(UserDao.class);
		userDao.save(user);

		System.out.println("******* call UserMapper.save");

		session.commit();
		session.close();
	}

	// 一对一测试
	public void testGetUser(int userId) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		UserDao userDao = session.getMapper(UserDao.class);
		User user = userDao.getUserById(userId);

		System.out.println("******* call userDao.getUserById");
		System.out.println("访问user时会再访问数据库获取card的数据信息，无论是否设置了懒加载，因为默认就是懒加载: " + user);
		System.out.println("访问user.getCard(): " + user.getCard());

		session.commit();
		session.close();
	}

	public void testGetCard(int id) throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		CardDao cardDao = session.getMapper(CardDao.class);
		Card card = cardDao.getCardById(id);

		System.out.println("******* call cardDao.testGetCard");
		System.out.println(card);

		session.commit();
		session.close();
	}

	// 一对多测试
	public void testGetUserWithAsset(int userId) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		UserDao userDao = session.getMapper(UserDao.class);
		User user = userDao.getUserWithAssetsById(userId);

		System.out.println("******* call userDao.testGetUserWithAsset");
		System.out.println("不访问user， 就不会方法数据库获取assets数据" + user);

		session.commit();
		session.close();
	}

	//多对一测试
	public void testGetAssetById(int id) throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();

		AssetDao assetDao = session.getMapper(AssetDao.class);
		Asset asset = assetDao.getAssetById(id);

		System.out.println("******* call assetDao.testGetAssetById");
		System.out.println(asset);

		session.commit();
		session.close();
	}
}

