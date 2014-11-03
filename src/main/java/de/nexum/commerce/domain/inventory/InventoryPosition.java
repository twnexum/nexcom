package de.nexum.commerce.domain.inventory;

import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface InventoryPosition {
	
	CartItem getCartItem();
	
	Integer getAvailableQuantity();
	
}
