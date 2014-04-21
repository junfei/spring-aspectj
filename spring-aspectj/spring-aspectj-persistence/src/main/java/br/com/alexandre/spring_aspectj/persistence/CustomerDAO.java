package br.com.alexandre.spring_aspectj.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import br.com.alexandre.spring_aspectj.cache.api.Cache;
import br.com.alexandre.spring_aspectj.persistence.dao.DAO;
import br.com.alexandre.spring_aspectj.persistence.entity.Customer;

@Component
public class CustomerDAO implements DAO<Customer, Long> {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Cache
	public Customer read(final Long id) {
		return this.entityManager.find(Customer.class, id);
	}

}
