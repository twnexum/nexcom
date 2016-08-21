package javax.commerce.domain.product.impl;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

import javax.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import javax.commerce.domain.product.Price;

/**
 * @author Thomas Weckert
 */
public class PriceImpl extends AbstractIdentifiableItemImpl implements Price {

	private static final long serialVersionUID = 3629149465167007838L;
	
	private BigDecimal amount;
	private Currency currency;
	private String productId;

	public PriceImpl(String productId, BigDecimal amount, String currencyCode) {
		this(UUID.randomUUID().toString(), productId, amount, Currency.getInstance(currencyCode));
	}
	
	public PriceImpl(String productId, BigDecimal amount, Currency currency) {
		this(UUID.randomUUID().toString(), productId, amount, currency);
	}
	
	public PriceImpl(String id, String productId, BigDecimal amount, String currencyCode) {
		this(id, productId, amount, Currency.getInstance(currencyCode));
	}
	
	public PriceImpl(String id, String productId, BigDecimal amount, Currency currency) {
		super(id);
		this.productId = productId;
		this.amount = amount;
		this.currency = currency;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (Price.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		Price otherPrice = (Price) obj;
		return otherPrice.getAmount().equals(this.getAmount())
				&& otherPrice.getCurrency().getCurrencyCode().equals(this.getCurrency().getCurrencyCode());
	}

	@Override
	public String getProductId() {
		return productId;
	}

	void setProductId(String productId) {
		this.productId = productId;
	}

}
