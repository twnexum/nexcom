package javax.commerce.domain.product;

import javax.commerce.domain.patterns.IdentifiableItem;

/**
 * @author Thomas Weckert
 */
public interface Attribute extends IdentifiableItem {

	String getKey();
	
	String getValue();
	
	String getProductId();
	
}
