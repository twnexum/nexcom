package de.nexum.commerce.domain.cart;

import java.util.Currency;
import java.util.List;

import de.nexum.commerce.domain.address.BillingAddress;
import de.nexum.commerce.domain.address.EmailAddress;
import de.nexum.commerce.domain.address.ShippingAddress;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.patterns.IdentifiableItem;
import de.nexum.commerce.domain.payment.Payment;
import de.nexum.commerce.domain.product.Price;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface ShoppingCart extends IdentifiableItem {
	
	CartPosition addCartPosition(CartPosition cartPosition);
	
	CartPosition removeCartPosition(CartPosition cartPosition);

	List<CartPosition> getCartPositions();
	
	ShippingAddress getShippingAddress();
	
	void setShippingAddress(ShippingAddress shippingAddress);
	
	BillingAddress getBillingAddress();
	
	void setBillingAddress(BillingAddress billingAddress);
	
	boolean containsCartPosition(CartItem cartItem);
	
	CartPosition getCartPosition(CartItem cartItem);
	
	Currency getCurrency();
	
	Price getTotalPrice();
	
	void setTotalPrice(Price price);
	
	Payment getPayment();
	
	void setPayment(Payment payment);
	
	EmailAddress getEmailAddress();
	
	void setEmailAddress(EmailAddress emailAddress);
	
}
