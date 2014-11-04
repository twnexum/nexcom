package de.nexum.commerce.domain.inventory.impl;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="inventoryCollection")
@TypeAlias("inventory")
public class InventoryPositionImpl extends AbstractIdentifiableItemImpl implements InventoryPosition {
	
	private Integer availableQuantity;
	@DBRef @Field("product") private CartItem cartItem;
	
	public InventoryPositionImpl(String id, CartItem cartItem, Integer availableQuantity) {
		
		super(id);
		
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
