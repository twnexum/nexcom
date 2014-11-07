package de.nexum.commerce.backend.dao;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface HelperDAO {
	
	Boolean isVariant(String cartItemId);

	Boolean isVariantProduct(String productId);
	
}
