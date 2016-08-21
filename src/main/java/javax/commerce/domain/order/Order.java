package javax.commerce.domain.order;

import javax.commerce.domain.patterns.IdentifiableItem;

/**
 * @author Thomas Weckert
 */
public interface Order extends IdentifiableItem {

	OrderStatus getStatus();
	
}
