package de.nexum.commerce.backend.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.inventory.impl.InventoryPositionImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PgGenericInventoryDAOImpl extends JdbcDaoSupport implements GenericDAO<InventoryPosition> {
	
	private static final String SQL_INSERT = "INSERT INTO INVENTORIES (ID, ITEM_ID, QUANTITY) VALUES (?,?,?)";
	private static final String SQL_DELETE = "DELETE FROM INVENTORIES WHERE ID = ?";
	private static final String SQL_SELECT = "SELECT ID, ITEM_ID, QUANTITY FROM INVENTORIES WHERE ITEM_ID = ?";
	private static final String SQL_SELECT_ALL = "SELECT ID, ITEM_ID, QUANTITY FROM INVENTORIES";

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public void save(InventoryPosition inventoryPosition) {
		getJdbcTemplate().update(
				SQL_INSERT,
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
		getJdbcTemplate().update(SQL_DELETE, new Object[] { inventoryPosition.getId() });		
	}

	@Override
	public InventoryPosition findByID(String id) {

		InventoryPosition inventoryPosition = (InventoryPosition) getJdbcTemplate().queryForObject(
			SQL_SELECT, new Object[] { id }, new RowMapper<InventoryPosition>() {

				@Override
				public InventoryPosition mapRow(ResultSet res, int rowNum) throws SQLException {
					return new InventoryPositionImpl(res.getString("ID"), res.getString("ITEM_ID"), res.getInt("QUANTITY"));
				}

			});

		return inventoryPosition;
	}

	@Override
	public Collection<InventoryPosition> findAll() {

		List<InventoryPosition> inventoryPositions = getJdbcTemplate().query(SQL_SELECT_ALL, new ResultSetExtractor<List<InventoryPosition>>() {
			
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
