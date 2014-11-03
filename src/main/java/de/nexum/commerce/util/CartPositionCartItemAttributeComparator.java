package de.nexum.commerce.util;

import java.util.Comparator;

import de.nexum.commerce.domain.cart.CartPosition;
import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class CartPositionCartItemAttributeComparator implements Comparator<CartPosition> {
	
	private String attribute;
	
	public CartPositionCartItemAttributeComparator(String attribute) {
	
		super();
		
		this.attribute = attribute;
	}

	@Override
	public int compare(CartPosition cartPosition1, CartPosition cartPosition2) {
		
		CartItem cartItem1 = cartPosition1.getCartItem();
		CartItem cartItem2 = cartPosition2.getCartItem();
		
		return cartItem1.getAttributes().get(attribute).compareTo(cartItem2.getAttributes().get(attribute));
	}

}