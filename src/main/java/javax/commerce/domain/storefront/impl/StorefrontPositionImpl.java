package javax.commerce.domain.storefront.impl;

import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.storefront.StorefrontPosition;

/**
 * @author Thomas Weckert
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
