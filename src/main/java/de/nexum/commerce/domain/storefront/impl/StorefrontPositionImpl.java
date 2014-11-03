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

	@Override
	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	

}
