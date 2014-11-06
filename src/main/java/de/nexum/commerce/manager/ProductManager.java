package de.nexum.commerce.manager;

import de.nexum.commerce.domain.product.Product;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface ProductManager {

	void insertProduct(Product product);
	
	Product findByID(String productID);
	
}
