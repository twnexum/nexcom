package de.nexum.commerce.domain.product.impl;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class DollarPriceImpl extends PriceImpl {

	public DollarPriceImpl(BigDecimal amount) {
		
		super(amount, "USD");
	}
	
}
