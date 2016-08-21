package javax.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.inventory.impl.InventoryPositionImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thomas Weckert
 */
public class GenericInventoryDAOImpl extends JdbcDaoSupport implements GenericDAO<InventoryPosition> {
	
	@Autowired
	private Properties queryProperties;

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(InventoryPosition inventoryPosition) {
		getJdbcTemplate().update(
				queryProperties.getProperty("insert_inventories"),
				new Object[] { inventoryPosition.getId(),
						inventoryPosition.getProductId(),
						inventoryPosition.getAvailableQuantity() });		
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(InventoryPosition inventoryPosition) {
		delete(inventoryPosition);
		save(inventoryPosition);
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void delete(InventoryPosition inventoryPosition) {
		getJdbcTemplate().update(queryProperties.getProperty("delete_inventories"), 
				new Object[] { inventoryPosition.getId() });		
	}

	@Override
	public InventoryPosition findByID(String id) {

		InventoryPosition inventoryPosition = (InventoryPosition) getJdbcTemplate().queryForObject(
				queryProperties.getProperty("select_inventories"), new Object[] { id }, new RowMapper<InventoryPosition>() {

				@Override
				public InventoryPosition mapRow(ResultSet res, int rowNum) throws SQLException {
					return new InventoryPositionImpl(res.getString("ID"), res.getString("ITEM_ID"), res.getInt("QUANTITY"));
				}

			});

		return inventoryPosition;
	}

	@Override
	public Collection<InventoryPosition> findAll() {

		List<InventoryPosition> inventoryPositions = getJdbcTemplate().query(queryProperties.getProperty("select_all_inventories"), 
				new ResultSetExtractor<List<InventoryPosition>>() {
			
			@Override
			public List<InventoryPosition> extractData(ResultSet res) throws SQLException, DataAccessException {
				
				List<InventoryPosition> inventoryPositions = new ArrayList<InventoryPosition>();
				while (res.next()) {
					inventoryPositions.add(new InventoryPositionImpl(res.getString("ID"), 
							res.getString("ITEM_ID"), res.getInt("QUANTITY")));
				}
				return inventoryPositions;
			}
			
		});
				
		return inventoryPositions;
	}
	


}
