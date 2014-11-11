package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.impl.PriceImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgGenericPriceDAOImpl extends JdbcDaoSupport implements GenericDAO<Price> {
	
	@Autowired
	private Properties queryProperties;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(Price price) {
		getJdbcTemplate().update(
				queryProperties.getProperty("insert_prices"),
				new Object[] { price.getId(), price.getProductId(),
						price.getAmount(), price.getCurrency().getCurrencyCode() });
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(Price price) {
		delete(price);
		save(price);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Price price) {
		getJdbcTemplate().update(queryProperties.getProperty("delete_prices"), new Object[] { price.getId() });
	}

	@Override
	public Price findByID(String itemId) {
		
		Price price = (Price) getJdbcTemplate().queryForObject(
				queryProperties.getProperty("select_prices"), new Object[] { itemId }, new RowMapper<Price>() {
	
				@Override
				public Price mapRow(ResultSet res, int rowNum) throws SQLException {
						return new PriceImpl(res.getString("ID"), 
								res.getString("ITEM_ID"),
								res.getBigDecimal("AMOUNT"), 
								res.getString("CURRENCY_CODE"));
				}	
			});
		
		return price;
	}

	@Override
	public Collection<Price> findAll() {
		throw new UnsupportedOperationException();
	}


}
