package javax.commerce.domain.cart.impl;

import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;

/**
 * @author Thomas Weckert
 */
public class CartPositionImpl implements CartPosition {
	
	private CartItem cartItem;
	private Integer quantity;
	private Price totalPrice;

	public CartPositionImpl(CartItem cartItem, Integer number) {
		
		super();
		
		this.cartItem = cartItem;
		this.quantity = number;
	}
	
	@Override
	public CartItem getCartItem() {
		return cartItem;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public Integer incrementQuantity(Integer quantity) {		
		this.quantity += quantity;
		return quantity;
	}

	@Override
	public Integer decrementQuantity(Integer quantity) {
		this.quantity -= quantity;
		return quantity;
	}

	@Override
	public Price getTotalPrice() {
		return totalPrice;
	}

	@Override
	public void setTotalPrice(Price totalPrice) {
		this.totalPrice = totalPrice;
	}

}
