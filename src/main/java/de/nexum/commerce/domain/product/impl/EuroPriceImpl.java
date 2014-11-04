package de.nexum.commerce.domain.product.impl;

import java.math.BigDecimal;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class EuroPriceImpl extends PriceImpl {

	public EuroPriceImpl(BigDecimal amount) {
		
		super(amount, "EUR");
	}
	
}
