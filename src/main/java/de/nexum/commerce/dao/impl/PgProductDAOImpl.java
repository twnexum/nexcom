package de.nexum.commerce.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.nexum.commerce.dao.GenericDAO;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.impl.ProductImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgProductDAOImpl extends JdbcDaoSupport implements GenericDAO<Product> {
	
	private static final String SQL_INSERT = "INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID) VALUES (?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM PRODUCTS WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, IS_VARIANT_PRODUCT FROM PRODUCTS WHERE ID = ?";
	
	private GenericDAO<Price> priceDAO;
	private GenericDAO<Set<Attribute>> attributeDAO;

	@Override
	public void save(Product product) {
		priceDAO.save(product.getPrice());
		attributeDAO.save(product.getAttributes());
		getJdbcTemplate().update(SQL_INSERT, new Object[] { product.getId(), product.isVariantProduct(), null });
	}

	@Override
	public void update(Product product) {
		delete(product);
		save(product);
	}

	@Override
	public void delete(Product product) {		
		priceDAO.delete(product.getPrice());
		attributeDAO.delete(product.getAttributes());
		getJdbcTemplate().update(SQL_DELETE, new Object[] { product.getId(), });
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
	
	public void setPriceDAO(GenericDAO<Price> priceDAO) {
		this.priceDAO = priceDAO;
	}

	public void setAttributeDAO(GenericDAO<Set<Attribute>> attributeDAO) {
		this.attributeDAO = attributeDAO;
	}

}
