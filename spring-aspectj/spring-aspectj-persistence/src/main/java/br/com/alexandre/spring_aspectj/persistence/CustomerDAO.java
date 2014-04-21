package br.com.alexandre.spring_aspectj.persistence;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.alexandre.spring_aspectj.cache.api.Cache;
import br.com.alexandre.spring_aspectj.persistence.entity.Customer;

@Component
public class CustomerDAO {
	
	@Cache
	public Customer findById(final Long id) {
		final Customer customer = new Customer();
		customer.setId(id);
		customer.setName("Customer #" + id.toString());
		customer.setSex("M");
		customer.setBirth(new Date());
		
		return customer; 
	}
}
