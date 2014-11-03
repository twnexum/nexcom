package de.nexum.commerce.domain.patterns;

import java.util.Map;


/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface CartItem extends IdentifiableItem, PricedItem {
	
	Map<String, String> getAttributes();
	
}
