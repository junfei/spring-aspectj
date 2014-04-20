package br.com.alexandre.spring_aspectj.persistence;

import org.springframework.stereotype.Component;

import br.com.alexandre.spring_aspectj.cache.api.Cache;
import br.com.alexandre.spring_aspectj.persistence.entity.Customer;

@Component
public class CustomerDAO {
	
	@Cache
	public Customer findById(final Long id) {
		return new Customer();
	}
}
