package javax.commerce.backend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.impl.VariantImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thomas Weckert
 */
public class GenericVariantCollectionDAOImpl extends JdbcDaoSupport implements GenericDAO<Set<Variant>> {
	
	@Autowired
	private Properties queryProperties;

	@Autowired
	private GenericDAO<Price> priceDAO;
	
	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(final Set<Variant> variants) {
		
		getJdbcTemplate().batchUpdate(queryProperties.getProperty("insert_variants_collection"), new BatchPreparedStatementSetter() {

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
		getJdbcTemplate().update(queryProperties.getProperty("delete_variants_collection"), new Object[] { variants.iterator().next().getProductId() });
	}

	@Override
	public Set<Variant> findByID(String itemId) {
		
		Set<Variant> variants = getJdbcTemplate().query(queryProperties.getProperty("select_variants_collection"), new ResultSetExtractor<Set<Variant>>() {
			
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
