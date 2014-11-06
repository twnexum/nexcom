package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.impl.PriceImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Repository
public class PgGenericPriceDAOImpl extends JdbcDaoSupport implements GenericDAO<Price> {
	
	private static final String SQL_INSERT = "INSERT INTO PRICES (ID, ITEM_ID, AMOUNT, CURRENCY_CODE) VALUES (?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM PRICES WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, ITEM_ID, AMOUNT, CURRENCY_CODE FROM PRICES WHERE ITEM_ID = ?";

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(Price price) {
		getJdbcTemplate().update(
				SQL_INSERT,
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
		getJdbcTemplate().update(SQL_DELETE, new Object[] { price.getId() });
	}

	@Override
	public Price findByID(String itemId) {
		
		Price price = (Price) getJdbcTemplate().queryForObject(
			SQL_SELECT, new Object[] { itemId }, new RowMapper<Price>() {
	
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
