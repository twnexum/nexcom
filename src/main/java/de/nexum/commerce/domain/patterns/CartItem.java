package de.nexum.commerce.domain.patterns;

import java.util.Set;

import de.nexum.commerce.domain.product.Attribute;


/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface CartItem extends IdentifiableItem, PricedItem {
	
	Set<Attribute> getAttributes();
	
}
