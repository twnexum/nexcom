package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import de.nexum.commerce.backend.dao.HelperDAO;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgHelperDAOImpl extends JdbcDaoSupport implements HelperDAO {

	@Override
	public Boolean isVariantProduct(String productId) {
		
		Boolean isVariantProduct = (Boolean) getJdbcTemplate().queryForObject(
			"SELECT IS_VARIANT_PRODUCT FROM PRODUCTS WHERE ID = ?", new Object[] { productId }, new RowMapper<Boolean>() {
	
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
			"SELECT COUNT(*) FROM PRODUCTS WHERE ID = ? AND IS_VARIANT_PRODUCT = FALSE AND ITEM_ID IS NOT NULL", 
			new Object[] { cartItemId }, Integer.class);
		
		return (found == 1);
	}

}
