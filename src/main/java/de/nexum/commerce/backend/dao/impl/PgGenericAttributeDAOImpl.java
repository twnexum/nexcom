package de.nexum.commerce.backend.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.impl.AttributeImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Repository
public class PgGenericAttributeDAOImpl extends JdbcDaoSupport implements GenericDAO<Set<Attribute>> {

	private static final String SQL_INSERT = "INSERT INTO ATTRIBUTES (ID, ITEM_ID, ATTR_KEY, ATTR_VALUE) VALUES (?,?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM ATTRIBUTES WHERE ITEM_ID = ?";
	private static final String SQL_SELECT = "SELECT ID, ITEM_ID, ATTR_KEY, ATTR_VALUE FROM ATTRIBUTES WHERE ITEM_ID = ?";
	
	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(final Set<Attribute> attributes) {

		getJdbcTemplate().batchUpdate(SQL_INSERT, new BatchPreparedStatementSetter() {

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
		getJdbcTemplate().update(SQL_DELETE, new Object[] { attributes.iterator().next().getProductId() });
	}

	@Override
	public Set<Attribute> findByID(String itemId) {
		
		Set<Attribute> attributes = getJdbcTemplate().query(SQL_SELECT, new ResultSetExtractor<Set<Attribute>>() {
			
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
