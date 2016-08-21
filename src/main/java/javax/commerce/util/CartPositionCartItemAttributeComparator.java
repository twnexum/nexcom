package javax.commerce.util;

import java.util.Comparator;
import java.util.Map;

import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.patterns.CartItem;

/**
 * @author Thomas Weckert
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
		Map<String, String> attributesMap1 = AttributeUtils.asMap(cartItem1.getAttributes());
		
		CartItem cartItem2 = cartPosition2.getCartItem();
		Map<String, String> attributesMap2 = AttributeUtils.asMap(cartItem2.getAttributes());
		
		return attributesMap1.get(attribute).compareTo(attributesMap2.get(attribute));
	}

}