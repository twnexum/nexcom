package de.nexum.commerce.domain.product.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import de.nexum.commerce.domain.product.Price;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class PriceImpl implements Price {

	public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

	private BigDecimal amount;
	private Currency currency;
	private RoundingMode rounding;

	public PriceImpl(BigDecimal amount, Currency currency, RoundingMode rounding) {

		super();

		this.currency = currency;
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), rounding);
	}

	@Override
	public String toString() {
		return new StringBuffer().append(this.currency.getSymbol()).append(" ").append(getAmount()).toString();
	}

	public String toString(Locale locale) {
		return new StringBuffer().append(this.currency.getSymbol(locale)).append(" ").append(getAmount()).toString();
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

}
