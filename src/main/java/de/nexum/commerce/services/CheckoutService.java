package de.nexum.commerce.services;

import de.nexum.commerce.domain.address.BillingAddress;
import de.nexum.commerce.domain.address.ShippingAddress;
import de.nexum.commerce.domain.cart.ShoppingCart;
import de.nexum.commerce.domain.order.Order;
import de.nexum.commerce.domain.payment.Payment;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface CheckoutService {

	Order checkout(ShoppingCart shoppingCart, Payment payment, BillingAddress billingAddress, ShippingAddress shippingAddress);
	
}
