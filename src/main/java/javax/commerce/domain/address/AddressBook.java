package javax.commerce.domain.address;

import java.util.List;

/**
 * @author Thomas Weckert
 */
public interface AddressBook {
	
	EmailAddress getEmailAddress();

	List<ShippingAddress> getShippingAddresses();
	
	List<BillingAddress> getBillingAddresses();
	
}
