package de.nexum.commerce.util;

import java.math.BigDecimal;
import java.util.Comparator;

import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class CartItemPriceComparator implements Comparator<CartItem> {
	
	private boolean compareAscending;
	
	public CartItemPriceComparator(boolean compareAscending) {
		
		super();
		
		this.compareAscending = compareAscending;
	}

	@Override
	public int compare(CartItem cartItem1, CartItem cartItem2) {
		
		BigDecimal amount1 = cartItem1.getPrice().getAmount();
		BigDecimal amount2 = cartItem2.getPrice().getAmount();
		
		if (!compareAscending) {
			return amount1.compareTo(amount2) * -1;
		}
		
		return amount1.compareTo(amount2);
	}

}
