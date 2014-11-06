package de.nexum.commerce.frontend.services;

import java.util.List;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.storefront.StorefrontPosition;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface StorefrontService {

	List<StorefrontPosition> getStorefrontPositions(List<InventoryPosition> inventoryPositions);
	
}
