package de.nexum.commerce.domain.address;

import java.util.List;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface AddressBook {
	
	EmailAddress getEmailAddress();

	List<ShippingAddress> getShippingAddresses();
	
	List<BillingAddress> getBillingAddresses();
	
}
