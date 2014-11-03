package de.nexum.commerce.domain.patterns;

import de.nexum.commerce.domain.product.Price;


/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface PricedItem {
	
	Price getPrice();
	
}
