package javax.commerce.domain.product;

import javax.commerce.domain.patterns.CartItem;

/**
 * @author Thomas Weckert
 */
public interface Product extends CartItem {
	
	Boolean isVariantProduct();

}
