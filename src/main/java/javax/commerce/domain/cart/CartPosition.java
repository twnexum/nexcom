package javax.commerce.domain.cart;

import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;

/**
 * @author Thomas Weckert
 */
public interface CartPosition {

	CartItem getCartItem();
	
	Integer getQuantity();
	
	Integer incrementQuantity(Integer quantity);
	
	Integer decrementQuantity(Integer quantity);
	
	Price getTotalPrice();
	
	void setTotalPrice(Price price);
	
}
