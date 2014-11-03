package de.nexum.commerce.domain.cart;

import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface CartPosition {

	CartItem getCartItem();
	
	Integer getQuantity();
	
	Integer incrementQuantity(Integer quantity);
	
	Integer decrementQuantity(Integer quantity);
	
	Price getTotalPrice();
	
	void setTotalPrice(Price price);
	
}
