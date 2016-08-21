package javax.commerce.domain.inventory.impl;

import java.util.UUID;

import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;

/**
 * @author Thomas Weckert
 */
public class InventoryPositionImpl extends AbstractIdentifiableItemImpl implements InventoryPosition {

	private static final long serialVersionUID = 1888082254115771264L;
	
	private Integer availableQuantity;
	private String productId;
	
	public InventoryPositionImpl(String productId, Integer availableQuantity) {
		this(UUID.randomUUID().toString(), productId, availableQuantity);
	}
	
	public InventoryPositionImpl(String id, String productId, Integer availableQuantity) {		
		super(id);		
		this.productId = productId;
		this.availableQuantity = availableQuantity;
	}

	@Override
	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	@Override
	public String getProductId() {
		return productId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (InventoryPosition.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		InventoryPosition otherInventoryPosition = (InventoryPosition) obj;
		return otherInventoryPosition.getProductId().equals(this.getProductId())
				&& otherInventoryPosition.getAvailableQuantity().equals(this.getAvailableQuantity());
	}

}
