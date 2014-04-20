package br.com.alexandre.spring_aspectj.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alexandre.spring_aspectj.persistence.entity.Customer;
import br.com.alexandre.spring_aspectj.service.CustomerService;

public class CustomerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final CustomerService customerService = (CustomerService) getServletContext().getAttribute("customerService");
		final Customer customer = customerService.findById(1L);
		System.out.println(customer);
	}

	
}
