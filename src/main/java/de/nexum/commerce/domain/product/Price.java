package de.nexum.commerce.domain.product;

import java.math.BigDecimal;
import java.util.Currency;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface Price extends IdentifiableItem {

	BigDecimal getAmount();
	
	Currency getCurrency();
	
	String getProductId();
	
}
