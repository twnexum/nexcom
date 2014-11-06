package de.nexum.commerce.domain.product;

import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface Product extends CartItem {
	
	Boolean isVariantProduct();

}
