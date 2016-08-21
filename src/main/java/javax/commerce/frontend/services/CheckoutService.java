package javax.commerce.frontend.services;

import javax.commerce.domain.address.BillingAddress;
import javax.commerce.domain.address.ShippingAddress;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.order.Order;
import javax.commerce.domain.payment.Payment;

/**
 * @author Thomas Weckert
 */
public interface CheckoutService {

	Order checkout(ShoppingCart shoppingCart, Payment payment, BillingAddress billingAddress, ShippingAddress shippingAddress);
	
}
