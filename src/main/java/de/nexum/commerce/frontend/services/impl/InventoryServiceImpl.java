package de.nexum.commerce.frontend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.nexum.commerce.backend.services.RepositoryService;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.inventory.impl.InventoryPositionImpl;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.frontend.services.InventoryService;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public List<InventoryPosition> getInventoryPositions(boolean excludeVariants) {
		
		Map<String, InventoryPosition> inventoryPositionsById = new HashMap<String, InventoryPosition>();

		List<InventoryPosition> inventoryPositions = repositoryService.findAllInventories();
		for (InventoryPosition nextInventoryPosition : inventoryPositions) {
			
			CartItem cartItem = repositoryService.findCartItemById(nextInventoryPosition.getProductId());
			
			if (Product.class.isAssignableFrom(cartItem.getClass())) {
				
				Product product = (Product) cartItem;
				if (product.isVariantProduct() == false || (product.isVariantProduct() == true && excludeVariants)) {
					inventoryPositionsById.put(product.getId(), nextInventoryPosition);
					continue;
				}
			}
			
			if (Variant.class.isAssignableFrom(cartItem.getClass())) {
				if (excludeVariants) {
					
					Variant variant = (Variant) cartItem;
					Product product = (Product) repositoryService.findCartItemById(variant.getProductId());
					inventoryPositionsById.put(product.getId(), new InventoryPositionImpl(product.getId(), Integer.valueOf(0)));
				} else {
					inventoryPositionsById.put(cartItem.getId(), nextInventoryPosition);
				}
			}
		}
		
		return new ArrayList<InventoryPosition>(inventoryPositionsById.values());
	}

	@Override
	public InventoryPosition findInventoryByCartItemId(String cartItemId) {
		
		return repositoryService.findInventoryByProductId(cartItemId);
	}



}
