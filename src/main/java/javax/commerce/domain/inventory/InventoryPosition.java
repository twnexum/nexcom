package javax.commerce.domain.inventory;

import javax.commerce.domain.patterns.IdentifiableItem;

/**
 * @author Thomas Weckert
 */
public interface InventoryPosition extends IdentifiableItem {
	
	String getProductId();
	
	Integer getAvailableQuantity();
	
}
