package de.nexum.commerce.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.domain.product.impl.VariantProductImpl;
import de.nexum.commerce.util.VariantAttributeUtils;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgGenericVariantProductDAOImpl extends JdbcDaoSupport implements GenericDAO<VariantProduct> {
	
	private static final String SQL_INSERT = "INSERT INTO PRODUCTS (ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES) VALUES (?,TRUE,NULL,?)";
	private static final String SQL_DELETE = "DELETE FROM PRODUCTS WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, IS_VARIANT_PRODUCT, ITEM_ID, VARIANT_ATTRIBUTES FROM PRODUCTS WHERE ID = ?";

	@Autowired
	private GenericDAO<Set<Attribute>> attributeDAO;
	
	@Autowired
	private GenericDAO<Set<Variant>> collectionVariantDAO;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(final VariantProduct variantProduct) {		
		
		attributeDAO.save(variantProduct.getAttributes());	
		collectionVariantDAO.save(variantProduct.getVariants());
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection conn) throws SQLException {
				
				String[] variantAttributeTuple = 
					VariantAttributeUtils.asArray(((VariantProduct) variantProduct).getVariantAttributeTuple()); 
				
				final PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
				pstmt.setString(1, variantProduct.getId().toString());
				pstmt.setArray(2, conn.createArrayOf("varchar", variantAttributeTuple));
				
				return pstmt;
			}
		});
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(VariantProduct variantProduct) {
		delete(variantProduct);
		save(variantProduct);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(VariantProduct variantProduct) {		
		attributeDAO.delete(variantProduct.getAttributes());
		collectionVariantDAO.delete(variantProduct.getVariants());
		getJdbcTemplate().update(SQL_DELETE, new Object[] { variantProduct.getId() });
	}

	@Override
	public VariantProduct findByID(String productId) {
		
		final Set<Attribute> attributes = attributeDAO.findByID(productId);
		final Set<Variant> variants = collectionVariantDAO.findByID(productId);

		VariantProduct product = (VariantProduct) getJdbcTemplate().queryForObject(
			SQL_SELECT, new Object[] { productId }, new RowMapper<Product>() {
	
				@Override
				public Product mapRow(ResultSet res, int rowNum) throws SQLException {					
					return new VariantProductImpl(res
							.getString("ID"), attributes, variants,
							VariantAttributeUtils.asSet((String[]) res.getArray("VARIANT_ATTRIBUTES").getArray()));
				}
	
			});
		
		return product;
	}

	@Override
	public Collection<VariantProduct> findAll() {
		throw new UnsupportedOperationException();
	}

}
