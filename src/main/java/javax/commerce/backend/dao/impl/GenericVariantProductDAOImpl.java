package javax.commerce.backend.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.VariantProduct;
import javax.commerce.domain.product.impl.VariantProductImpl;
import javax.commerce.util.VariantAttributeUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thomas Weckert
 */
public class GenericVariantProductDAOImpl extends JdbcDaoSupport implements GenericDAO<VariantProduct> {

	@Autowired
	private Properties queryProperties;
	
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
				
				final PreparedStatement pstmt = conn.prepareStatement(queryProperties.getProperty("insert_variant_products"));
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
		getJdbcTemplate().update(queryProperties.getProperty("delete_variant_products"), new Object[] { variantProduct.getId() });
	}

	@Override
	public VariantProduct findByID(String productId) {
		
		final Set<Attribute> attributes = attributeDAO.findByID(productId);
		final Set<Variant> variants = collectionVariantDAO.findByID(productId);

		VariantProduct product = (VariantProduct) getJdbcTemplate().queryForObject(
				queryProperties.getProperty("select_variant_products"), new Object[] { productId }, new RowMapper<Product>() {
	
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
