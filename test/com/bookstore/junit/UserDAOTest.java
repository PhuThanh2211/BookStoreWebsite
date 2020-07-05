package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;

public class UserDAOTest extends BaseDAOTest {
	
	private static UserDAO userDao;
	
	@BeforeClass
	public static void setup() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		userDao = new UserDAO(entityManager);
	}
	
	@Test
	public void testCreateUsers() {
		Users theUser = new Users("barathrum@dota.com", "Spirit Breaker", "1234");
		userDao.create(theUser);
		
		assertTrue(theUser.getUserId() > 0);
	}
	
	@Test(expected = PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users theUser = new Users();
		userDao.create(theUser);
	}
	
	@Test
	public void testUpdateUsers() {
		int userId = 2;
		Users theUser = userDao.get(userId);
		theUser.setPassword("12345");
		
		userDao.update(theUser);
		
		assertEquals(theUser.getPassword(), "12345");
	}
	
	@Test
	public void testUserFound() {
		int userId = 1;
		Users theUser = userDao.get(userId);
		
		assertNotNull(theUser);
	}
	
	@Test
	public void testUserNotFound() {
		int userId = 0;
		Users theUser = userDao.get(userId);
		
		assertNull(theUser);
	}
	
	@Test
	public void testDeleteUser() {
		int userId = 2;
		userDao.delete(userId);
		
		Users theUser = userDao.get(userId);
		assertNull(theUser);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteUserNotExist() {
		int userId = 0;
		userDao.delete(userId);
	}
	
	@Test
	public void testListAll() {
		List<Users> listUsers = userDao.listAll();

		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testCountAll() {
		List<Users> listUsers = userDao.listAll();
		
		long allUsers = userDao.count();
		
		assertTrue(allUsers == listUsers.size());
	}
	
	@Test
	public void testFindByEmail() {
		String email = "airness@gmail.com";
		Users theUser = userDao.findByEmail(email);
		
		assertNotNull(theUser);
	}
	
	@Test
	public void testCheckLoginSuccess() {
		String email = "airness@gmail.com";
		String password = "1234";
		
		boolean isLogin = userDao.checkLogin(email, password);
		assertTrue(isLogin);
	}
	
	@Test
	public void testCheckLoginFailed() {
		String email = "airness@gmail.com";
		String password = "12345";
		
		boolean isLogin = userDao.checkLogin(email, password);
		assertFalse(isLogin);
	}

	public static void tearDown() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}
	
}
