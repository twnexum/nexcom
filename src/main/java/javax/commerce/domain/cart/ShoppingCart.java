package javax.commerce.domain.cart;

import java.util.Currency;
import java.util.List;

import javax.commerce.domain.address.BillingAddress;
import javax.commerce.domain.address.EmailAddress;
import javax.commerce.domain.address.ShippingAddress;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.patterns.IdentifiableItem;
import javax.commerce.domain.payment.Payment;
import javax.commerce.domain.product.Price;

/**
 * @author Thomas Weckert
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
