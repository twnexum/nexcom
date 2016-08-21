package javax.commerce.frontend.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.inventory.impl.InventoryPositionImpl;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.frontend.services.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thomas Weckert
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
					inventoryPositionsById.put(variant.getProductId(), new InventoryPositionImpl(variant.getProductId(), Integer.valueOf(0)));
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
