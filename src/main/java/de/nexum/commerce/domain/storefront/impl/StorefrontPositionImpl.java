package de.nexum.commerce.domain.storefront.impl;

import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.storefront.StorefrontPosition;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class StorefrontPositionImpl implements StorefrontPosition {
	
	private CartItem cartItem;
	private Price price;
	
	public StorefrontPositionImpl(CartItem cartItem, Price price) {
		super();
		this.cartItem = cartItem;
		this.price = price;
	}

	@Override
	public CartItem getCartItem() {
		return cartItem;
	}

	@Override
	public Price getPrice() {
		return price;
	}	

}
