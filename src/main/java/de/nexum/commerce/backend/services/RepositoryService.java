package de.nexum.commerce.backend.services;

import java.util.List;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.product.Product;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface RepositoryService {

	void saveProduct(Product product);
	
	Product findProductByID(String productID);
	
	void saveInventory(InventoryPosition inventoryPosition);
	
	InventoryPosition findInventoryByProductId(String productID);
	
	List<InventoryPosition> findAllInventories();
	
}
