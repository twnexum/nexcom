package javax.commerce.backend.services;

import java.util.List;

import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Product;

/**
 * @author Thomas Weckert
 */
public interface RepositoryService {

	void saveProduct(Product product);
	
	CartItem findCartItemById(String cartItemId);
	
	void saveInventory(InventoryPosition inventoryPosition);
	
	InventoryPosition findInventoryByProductId(String productID);
	
	List<InventoryPosition> findAllInventories();
	
}
