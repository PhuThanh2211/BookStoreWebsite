package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.entity.Category;

public class CategoryDAOTest extends BaseDAOTest {
	
	private static CategoryDAO categoryDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		categoryDao = new CategoryDAO(entityManager);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreateCategory() {
		Category category = categoryDao.create(new Category("JavaScript"));
		
		assertTrue(category != null && category.getCategoryId() > 0);
	}

	@Test
	public void testUpdateCategory() {
		int categoryId = 2;
		Category theCategory = categoryDao.get(categoryId);
		theCategory.setName("Python");
		Category updatedCategory = categoryDao.update(theCategory);
		
		assertEquals(updatedCategory.getName(), "Python");
		
	}

	@Test
	public void testGet() {
		int categoryId = 2;
		Category theCategory = categoryDao.get(categoryId);
		
		assertEquals(theCategory.getName(), "Python");
	}

	@Test
	public void testDeleteObject() {
		int categoryId = 2;
		categoryDao.delete(categoryId);
		Category theCategory = categoryDao.get(categoryId);
		
		assertNull(theCategory);
	}

	@Test
	public void testListAll() {
		List<Category> listCategories = categoryDao.listAll();
		
		assertTrue(listCategories.size() > 0);
	}

	@Test
	public void testCount() {
		long records = categoryDao.count();

		assertTrue(records == 2);
	}
	
	@Test
	public void testFindByName() {
		String name = "Java";
		Category theCategory = categoryDao.findByName(name);
		
		assertTrue(theCategory.getCategoryId() == 1);
	}

}
