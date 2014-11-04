package de.nexum.commerce.domain.product.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import de.nexum.commerce.domain.product.Price;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="prices")
@TypeAlias("price")
public class PriceImpl implements Price {

	public static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

	private BigDecimal amount;
	private String currencyCode;
	@Transient private Currency currency;
	@Transient private RoundingMode roundingMode;

	public PriceImpl(BigDecimal amount, String currencyCode) {
		
		this(amount, Currency.getInstance(currencyCode), DEFAULT_ROUNDING);
	}
	
	public PriceImpl(BigDecimal amount, String currencyCode, int roundingMode) {
		
		this(amount, Currency.getInstance(currencyCode), RoundingMode.valueOf(roundingMode));
	}
	
	public PriceImpl(BigDecimal amount, Currency currency, RoundingMode roundingMode) {

		super();

		this.currencyCode = currency.getCurrencyCode();
		this.currency = currency;
		this.roundingMode = roundingMode;
		this.amount = amount.setScale(currency.getDefaultFractionDigits(), roundingMode);
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount.setScale(this.currency.getDefaultFractionDigits(), roundingMode);
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

	public String getCurrencyCode() {
		return currencyCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (!PriceImpl.class.isAssignableFrom(obj.getClass())) {
            return false;
		}
        
		if (obj == this) {
            return true;
		}
		
		PriceImpl otherPrice = (PriceImpl) obj;
		return otherPrice.getAmount().equals(this.getAmount()) && otherPrice.getCurrency().equals(this.getCurrency());
	}

}
