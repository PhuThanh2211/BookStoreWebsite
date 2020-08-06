package com.bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.entity.Book;

public class ShoppingCartTest {
	
	private static ShoppingCart cart;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cart = new ShoppingCart();
		
		Book book = new Book(2);
		
		cart.addItem(book);
		cart.addItem(book);
	}
	
	@Test
	public void testAddItem() {
		
		Map<Book, Integer> items = cart.getItems();
		int quantity = items.get(new Book(2));
		
		assertEquals(2, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		Book book = new Book(3);
		cart.addItem(book);
		
		cart.removeItem(book);
		
		assertNull(cart.getItems().get(book));
	}
	
	@Test
	public void testGetTotalQuantity() {
		Book book = new Book(3);
		cart.addItem(book);
		cart.addItem(book);
		cart.addItem(book);		
		
		assertEquals(5, cart.getTotalQuantity());
	}
	
	@Test
	public void testGetTotalAmount() {
		Book book = new Book(3);
		cart.addItem(book);
		cart.addItem(book);
		cart.addItem(book);		
		
		assertEquals(180, cart.getTotalAmount());
	}
	
	@Test
	public void testClear() {
		cart.clear();
		
		assertEquals(cart.getTotalQuantity(), 0);
	}

}
