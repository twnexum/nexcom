package javax.commerce.util;

import java.math.BigDecimal;
import java.util.Comparator;

import javax.commerce.domain.patterns.CartItem;

/**
 * @author Thomas Weckert
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
