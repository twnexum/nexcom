package de.nexum.commerce.domain.order;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface Order extends IdentifiableItem {

	OrderStatus getStatus();
	
}
