package javax.commerce.backend.dao;

import java.util.Collection;

/**
 * @author Thomas Weckert
 */
public interface GenericDAO<T> {
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
	
	T findByID(String id);
	
	Collection<T> findAll();

}
