package javax.commerce.frontend.services;

import java.util.List;

import javax.commerce.domain.inventory.InventoryPosition;

/**
 * @author Thomas Weckert
 */
public interface InventoryService {

	List<InventoryPosition> getInventoryPositions(boolean excludeVariants);
	
	InventoryPosition findInventoryByCartItemId(String cartItemId);
	
}
