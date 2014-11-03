package de.nexum.commerce.domain.inventory.impl;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class InventoryPositionImpl implements InventoryPosition {
	
	private Integer availableQuantity;
	private CartItem cartItem;
	
	public InventoryPositionImpl(CartItem cartItem, Integer availableQuantity) {
		
		super();
		
		this.cartItem = cartItem;
		this.availableQuantity = availableQuantity;
	}

	@Override
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	@Override
	public CartItem getCartItem() {
		return cartItem;
	}

}
