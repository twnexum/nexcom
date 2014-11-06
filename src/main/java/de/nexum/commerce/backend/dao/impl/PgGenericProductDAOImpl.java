package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.impl.ProductImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Repository
public class PgGenericProductDAOImpl extends JdbcDaoSupport implements GenericDAO<Product> {
	
	private static final String SQL_INSERT = "INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES (?,FALSE,NULL,NULL)";
	private static final String SQL_DELETE = "DELETE FROM PRODUCTS WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES FROM PRODUCTS WHERE ID = ?";
	
	@Autowired
	private GenericDAO<Price> priceDAO;
	
	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(Product product) {				
		priceDAO.save(product.getPrice());
		attributeDAO.save(product.getAttributes());
		getJdbcTemplate().update(SQL_INSERT, new Object[] { product.getId() });
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
		getJdbcTemplate().update(SQL_DELETE, new Object[] { product.getId() });
	}

	@Override
	public Product findByID(String productId) {
		
		final Price price = priceDAO.findByID(productId);
		final Set<Attribute> attributes = attributeDAO.findByID(productId);

		Product product = (Product) getJdbcTemplate().queryForObject(
			SQL_SELECT, new Object[] { productId }, new RowMapper<Product>() {
	
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
