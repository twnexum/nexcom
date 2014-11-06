package de.nexum.commerce.domain.inventory;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface InventoryPosition extends IdentifiableItem {
	
	String getProductId();
	
	Integer getAvailableQuantity();
	
}
