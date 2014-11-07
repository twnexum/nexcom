package de.nexum.commerce.util;

import java.util.Comparator;
import java.util.Map;

import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.storefront.StorefrontPosition;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class StorefrontPositionCartItemAttributeComparator implements Comparator<StorefrontPosition> {
	
	private String attribute;
	
	public StorefrontPositionCartItemAttributeComparator(String attribute) {
		super();
		this.attribute = attribute;
	}

	@Override
	public int compare(StorefrontPosition storefrontPosition1, StorefrontPosition storefrontPosition2) {

		CartItem cartItem1 = storefrontPosition1.getCartItem();
		Map<String, String> attributesMap1 = AttributeUtils.asMap(cartItem1.getAttributes());
		
		CartItem cartItem2 = storefrontPosition2.getCartItem();
		Map<String, String> attributesMap2 = AttributeUtils.asMap(cartItem2.getAttributes());
		
		return attributesMap1.get(attribute).compareTo(attributesMap2.get(attribute));
	}

}
