package de.nexum.commerce.domain.product;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface Attribute extends IdentifiableItem {

	String getKey();
	
	String getValue();
	
	String getProductId();
	
}
