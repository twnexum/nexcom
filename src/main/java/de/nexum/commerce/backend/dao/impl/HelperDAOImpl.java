package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.nexum.commerce.backend.dao.HelperDAO;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class HelperDAOImpl extends JdbcDaoSupport implements HelperDAO {
	
	@Autowired
	private Properties queryProperties;

	@Override
	public Boolean isVariantProduct(String productId) {
		
		Boolean isVariantProduct = (Boolean) getJdbcTemplate().queryForObject(
			queryProperties.getProperty("check_is_variant_product_for_productId"), new Object[] { productId }, new RowMapper<Boolean>() {
	
				@Override
				public Boolean mapRow(ResultSet res, int rowNum) throws SQLException {
					return res.getBoolean("IS_VARIANT_PRODUCT");
				}
			});
		
		return isVariantProduct;
	}

	@Override
	public Boolean isVariant(String cartItemId) {

		int found = getJdbcTemplate().queryForObject(
			queryProperties.getProperty("check_is_variant_for_cartItemId"), 
			new Object[] { cartItemId }, Integer.class);
		
		return (found == 1);
	}

}
