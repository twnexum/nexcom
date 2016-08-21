package javax.commerce.domain.product;

import java.math.BigDecimal;
import java.util.Currency;

import javax.commerce.domain.patterns.IdentifiableItem;

/**
 * @author Thomas Weckert
 */
public interface Price extends IdentifiableItem {

	BigDecimal getAmount();
	
	Currency getCurrency();
	
	String getProductId();
	
}
