package javax.commerce.domain.product;

import javax.commerce.domain.patterns.CartItem;


/**
 * @author Thomas Weckert
 */
public interface Variant extends CartItem {
	
	String getProductId();
	
}
