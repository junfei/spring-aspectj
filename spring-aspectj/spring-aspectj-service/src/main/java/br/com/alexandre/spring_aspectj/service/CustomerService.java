package br.com.alexandre.spring_aspectj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alexandre.spring_aspectj.persistence.CustomerDAO;
import br.com.alexandre.spring_aspectj.persistence.entity.Customer;

@Component
public class CustomerService {

	private final CustomerDAO customerDAO;

	@Autowired
	public CustomerService(final CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public Customer findById(final Long id) {
		return this.customerDAO.findById(id);
	}
}
