package de.nexum.commerce.domain.storefront;

import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface StorefrontPosition {

	CartItem getCartItem();
	
	Price getPrice();
	
}
