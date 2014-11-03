package de.nexum.commerce.domain.product;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface Price {

	BigDecimal getAmount();
	
	Currency getCurrency();
	
}
