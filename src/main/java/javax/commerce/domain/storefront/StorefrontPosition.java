package javax.commerce.domain.storefront;

import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;

/**
 * @author Thomas Weckert
 */
public interface StorefrontPosition {

	CartItem getCartItem();
	
	Price getPrice();
	
}
