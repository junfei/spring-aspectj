package br.com.alexandre.spring_aspectj.persistence.dao;

import java.io.Serializable;

public interface DAO<T, ID extends Serializable> {
	public T read(final ID id);
}
