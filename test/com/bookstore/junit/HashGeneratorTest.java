package com.bookstore.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.bookstore.dao.HashGeneration;

public class HashGeneratorTest {
	
	@Test
	public void testGeneratorMD5() {
		String password = "1234";
		String encryptedPassword = HashGeneration.generateMD5(password);
		
		System.out.println(encryptedPassword);
		assertTrue(true);
	}

}
