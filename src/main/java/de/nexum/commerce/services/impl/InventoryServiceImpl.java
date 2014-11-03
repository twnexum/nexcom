package de.nexum.commerce.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.inventory.impl.InventoryPositionImpl;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.services.InventoryService;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Service
public class InventoryServiceImpl implements InventoryService, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public List<InventoryPosition> getInventoryPositions(boolean excludeVariants) {

		List<InventoryPosition> inventoryPositions = new ArrayList<InventoryPosition>();
		
		Map<String, InventoryPositionImpl> inventoryPositionsById = applicationContext.getBeansOfType(de.nexum.commerce.domain.inventory.impl.InventoryPositionImpl.class);
		for (InventoryPositionImpl nextInventoryPosition : inventoryPositionsById.values()) {
			
			CartItem cartItem = nextInventoryPosition.getCartItem();
			if (excludeVariants) {
				if (Product.class.isAssignableFrom(cartItem.getClass()) || VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
					inventoryPositions.add(nextInventoryPosition);
				}
			} else {
				if (!VariantProduct.class.isAssignableFrom(cartItem.getClass()) && (Product.class.isAssignableFrom(cartItem.getClass()) || Variant.class.isAssignableFrom(cartItem.getClass()))) {
					inventoryPositions.add(nextInventoryPosition);
				}
			}
		}
		
		return inventoryPositions;
	}

	@Override
	public InventoryPosition findInventoryByCartItemId(String cartItemId) {
		
		Map<String, InventoryPositionImpl> inventoryPositionsById = applicationContext.getBeansOfType(InventoryPositionImpl.class);
		for (InventoryPositionImpl nextInventoryPosition : inventoryPositionsById.values()) {
			if (nextInventoryPosition.getCartItem().getId().equalsIgnoreCase(cartItemId)) {
				return nextInventoryPosition;
			}
		}

		return null;
	}



}
