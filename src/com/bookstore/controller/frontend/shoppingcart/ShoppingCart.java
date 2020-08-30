package com.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.bookstore.entity.Book;

public class ShoppingCart {
	private Map<Book, Integer> cart = new HashMap<>();
	
	public void addItem(Book book) {
		if (cart.containsKey(book)) {
			int quantity = cart.get(book) + 1;
			cart.put(book, quantity);
		} else {
			cart.put(book, 1);
		}
	}
	
	public void removeItem(Book book) {
		this.cart.remove(book);
	}
	
	public int getTotalQuantity() {
		int total = 0;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		
		while (iterator.hasNext()) {
			Book next = iterator.next();
			int quantity = cart.get(next);
			total += quantity;
		}
		
		return total;
	}
	
	public float getTotalAmount() {
		float totalAmount = 0.0f;
		
		Iterator<Book> iterator = cart.keySet().iterator();
		
		while (iterator.hasNext()) {
			Book next = iterator.next();
			int quantity = cart.get(next);
			float amount = next.getPrice() * quantity;
			totalAmount += amount;
		}
		
		return totalAmount;
	}
	
	public void updateCart(int[] bookIds, int[] quantities) {
		for (int i = 0; i < bookIds.length; i++) {
			Book key = new Book(bookIds[i]);
			int value = quantities[i];
			
			cart.put(key, value);
		}
	}
	
	public void clear() {
		cart.clear();
	}
	
	public int getTotalItems() {
		return this.cart.size();
	}
	
	public Map<Book, Integer> getItems() {
		return this.cart;
	}
}
