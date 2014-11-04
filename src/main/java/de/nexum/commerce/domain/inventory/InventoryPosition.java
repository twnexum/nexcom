package de.nexum.commerce.domain.inventory;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="inventoryCollection")
@TypeAlias("inventory")
public interface InventoryPosition extends IdentifiableItem {
	
	CartItem getCartItem();
	
	Integer getAvailableQuantity();
	
}
