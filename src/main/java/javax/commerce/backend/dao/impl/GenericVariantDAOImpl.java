package javax.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.impl.VariantImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * @author Thomas Weckert
 */
public class GenericVariantDAOImpl extends JdbcDaoSupport implements GenericDAO<Variant> {
	
	@Autowired
	private Properties queryProperties;
	
	@Autowired
	private GenericDAO<Price> priceDAO;
	
	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;
	
	@Override
	public void save(Variant variant) {
		getJdbcTemplate().update(queryProperties.getProperty("insert_variants"), new Object[] { variant.getId(), variant.getProductId() });		
	}
	
	@Override
	public void update(Variant variant) {
		delete(variant);
		save(variant);		
	}
	
	@Override
	public void delete(Variant variant) {
		getJdbcTemplate().update(queryProperties.getProperty("delete_variants"), new Object[] { variant.getId() });		
	}
	
	@Override
	public Variant findByID(String id) {
		
		final Price price = priceDAO.findByID(id);
		final Set<Attribute> attributes = attributeDAO.findByID(id);

		Variant variant = (Variant) getJdbcTemplate().queryForObject(
				queryProperties.getProperty("select_variants"), new Object[] { id }, new RowMapper<Variant>() {
	
				@Override
				public Variant mapRow(ResultSet res, int rowNum) throws SQLException {
					return new VariantImpl(res.getString("ID"), res.getString("ITEM_ID"), attributes, price);
				}	
			});
		
		return variant;
	}
	
	@Override
	public Collection<Variant> findAll() {
		throw new UnsupportedOperationException();
	}
	
}
