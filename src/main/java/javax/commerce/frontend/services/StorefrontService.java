package javax.commerce.frontend.services;

import java.util.List;

import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.storefront.StorefrontPosition;

/**
 * @author Thomas Weckert
 */
public interface StorefrontService {

	List<StorefrontPosition> getStorefrontPositions(List<InventoryPosition> inventoryPositions);
	
}
