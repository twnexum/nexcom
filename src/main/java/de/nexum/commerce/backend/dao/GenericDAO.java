package de.nexum.commerce.backend.dao;

import java.util.Collection;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface GenericDAO<T> {
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
	
	T findByID(String id);
	
	Collection<T> findAll();

}
