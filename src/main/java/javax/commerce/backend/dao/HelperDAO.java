package javax.commerce.backend.dao;

/**
 * @author Thomas Weckert
 */
public interface HelperDAO {
	
	Boolean isVariant(String cartItemId);

	Boolean isVariantProduct(String productId);
	
}
