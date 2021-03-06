package javax.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.impl.ProductImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thomas Weckert
 */
public class GenericProductDAOImpl extends JdbcDaoSupport implements GenericDAO<Product> {
	
	@Autowired
	private Properties queryProperties;
	
	@Autowired
	private GenericDAO<Price> priceDAO;
	
	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(Product product) {				
		priceDAO.save(product.getPrice());
		attributeDAO.save(product.getAttributes());
		getJdbcTemplate().update(queryProperties.getProperty("insert_products"), new Object[] { product.getId() });
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(Product product) {
		delete(product);
		save(product);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Product product) {		
		priceDAO.delete(product.getPrice());
		attributeDAO.delete(product.getAttributes());
		getJdbcTemplate().update(queryProperties.getProperty("delete_products"), new Object[] { product.getId() });
	}

	@Override
	public Product findByID(String productId) {
		
		final Price price = priceDAO.findByID(productId);
		final Set<Attribute> attributes = attributeDAO.findByID(productId);

		Product product = (Product) getJdbcTemplate().queryForObject(
				queryProperties.getProperty("select_products"), new Object[] { productId }, new RowMapper<Product>() {
	
				@Override
				public Product mapRow(ResultSet res, int rowNum) throws SQLException {
					return new ProductImpl(res.getString("ID"), attributes, price);
				}
	
			});
		
		return product;
	}

	@Override
	public Collection<Product> findAll() {
		throw new UnsupportedOperationException();
	}


}
