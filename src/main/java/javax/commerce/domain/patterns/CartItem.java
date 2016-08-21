package javax.commerce.domain.patterns;

import java.util.Map;
import java.util.Set;

import javax.commerce.domain.product.Attribute;


/**
 * @author Thomas Weckert
 */
public interface CartItem extends IdentifiableItem, PricedItem {
	
	Set<Attribute> getAttributes();
	
	Map<String, String> getAttributesMap();
	
}
