package de.nexum.commerce.backend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.impl.VariantImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgGenericCollectionVariantDAOImpl extends JdbcDaoSupport implements GenericDAO<Set<Variant>> {
	
	private static final String SQL_INSERT = "INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES (?,FALSE,?,NULL)";
	private static final String SQL_DELETE = "DELETE FROM PRODUCTS WHERE ITEM_ID = ?";
	private static final String SQL_SELECT = "SELECT ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES FROM PRODUCTS WHERE ITEM_ID = ?";

	@Autowired
	private GenericDAO<Price> priceDAO;
	
	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(final Set<Variant> variants) {
		
		getJdbcTemplate().batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {

			final List<Variant> variantList = new ArrayList<Variant>(variants);

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				Variant variant = variantList.get(i);
				
				String id = variant.getId();
				String itemId = variant.getProductId();

				ps.setString(1, id);
				ps.setString(2, itemId);
			}

			@Override
			public int getBatchSize() {
				return variantList.size();
			}
		});
		
		for (Variant nextVariant : variants) {
			priceDAO.save(nextVariant.getPrice());
			attributeDAO.save(nextVariant.getAttributes());	
		}
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(Set<Variant> variants) {
		delete(variants);
		save(variants);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Set<Variant> variants) {		
		getJdbcTemplate().update(SQL_DELETE, new Object[] { variants.iterator().next().getProductId() });
	}

	@Override
	public Set<Variant> findByID(String itemId) {
		
		Set<Variant> variants = getJdbcTemplate().query(SQL_SELECT, new ResultSetExtractor<Set<Variant>>() {
			
			@Override
			public Set<Variant> extractData(ResultSet res) throws SQLException, DataAccessException {
				
				Set<Variant> variants = new HashSet<Variant>();
				while (res.next()) {
					
					String variantId = res.getString("ID");
					String productId = res.getString("ITEM_ID");
					
					Price price = priceDAO.findByID(variantId);
					Set<Attribute> attributes = attributeDAO.findByID(variantId);
					
					variants.add(new VariantImpl(variantId, productId, attributes, price));
				}
				return variants;
			}
			
		}, itemId);
				
		return variants;
	}

	@Override
	public Collection<Set<Variant>> findAll() {
		throw new UnsupportedOperationException();
	}

}
