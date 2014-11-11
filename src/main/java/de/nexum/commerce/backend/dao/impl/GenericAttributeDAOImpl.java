package de.nexum.commerce.backend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
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
import de.nexum.commerce.domain.product.impl.AttributeImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class GenericAttributeDAOImpl extends JdbcDaoSupport implements GenericDAO<Set<Attribute>> {
	
	@Autowired
	private Properties queryProperties;
	
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(final Set<Attribute> attributes) {

		getJdbcTemplate().batchUpdate(queryProperties.getProperty("insert_attributes"), new BatchPreparedStatementSetter() {

			final List<Attribute> attributeList = new ArrayList<Attribute>(attributes);

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				Attribute attribute = attributeList.get(i);
				
				String id = attribute.getId();
				String key = attribute.getKey();
				String value = attribute.getValue();

				ps.setString(1, id);
				ps.setString(2, attribute.getProductId());
				ps.setString(3, key);
				ps.setString(4, value);
			}

			@Override
			public int getBatchSize() {
				return attributeList.size();
			}
		});
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(Set<Attribute> attributes) {
		delete(attributes);
		save(attributes);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(Set<Attribute> attributes) {
		getJdbcTemplate().update(queryProperties.getProperty("delete_attributes"), new Object[] { attributes.iterator().next().getProductId() });
	}

	@Override
	public Set<Attribute> findByID(String itemId) {
		
		Set<Attribute> attributes = getJdbcTemplate().query(queryProperties.getProperty("select_attributes"), new ResultSetExtractor<Set<Attribute>>() {
			
			@Override
			public Set<Attribute> extractData(ResultSet res) throws SQLException, DataAccessException {
				
				Set<Attribute> attributes = new HashSet<Attribute>();
				while (res.next()) {
					attributes.add(new AttributeImpl(res.getString("ID"), 
							res.getString("ITEM_ID"), res.getString("ATTR_KEY"), 
							res.getString("ATTR_VALUE")));
				}
				return attributes;
			}
			
		}, itemId);
				
		return attributes;
	}

	@Override
	public Collection<Set<Attribute>> findAll() {
		throw new UnsupportedOperationException();
	}

}
