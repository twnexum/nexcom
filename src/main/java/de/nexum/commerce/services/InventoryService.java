package de.nexum.commerce.services;

import java.util.List;

import de.nexum.commerce.domain.inventory.InventoryPosition;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface InventoryService {

	List<InventoryPosition> getInventoryPositions(boolean excludeVariants);
	
	InventoryPosition findInventoryByCartItemId(String cartItemId);
	
}
