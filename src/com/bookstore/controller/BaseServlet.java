package com.bookstore.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class BaseServlet extends HttpServlet {

	private static final long serialVersionID = 1L;
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;

	@Override
	public void init() throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	@Override
	public void destroy() {
		this.entityManager.close();
		this.entityManagerFactory.close();
	}

}
