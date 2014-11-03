package de.nexum.commerce.domain.product.impl;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class DollarPriceImpl extends PriceImpl {

	public DollarPriceImpl(BigDecimal amount) {
		
		super(amount, Currency.getInstance("USD"), PriceImpl.DEFAULT_ROUNDING);
	}
	
}
