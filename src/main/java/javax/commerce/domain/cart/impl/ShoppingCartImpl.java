package javax.commerce.domain.cart.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.commerce.domain.address.BillingAddress;
import javax.commerce.domain.address.EmailAddress;
import javax.commerce.domain.address.ShippingAddress;
import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import javax.commerce.domain.payment.Payment;
import javax.commerce.domain.product.Price;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Thomas Weckert
 */
@Component("shoppingCart")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class ShoppingCartImpl extends AbstractIdentifiableItemImpl implements ShoppingCart, Serializable {

	private static final long serialVersionUID = 8739750058472597752L;
	
	private Map<String, CartPosition> cartPositionsById;
	private Currency currency;
	private Price totalPrice;
	private BillingAddress billingAddress;
	private ShippingAddress shippingAddress;
	private EmailAddress emailAddress;
	private Payment payment;
	
	public ShoppingCartImpl() {
		this(UUID.randomUUID().toString());
	}
	
	public ShoppingCartImpl(String id) {
		super(id);		
		this.cartPositionsById = new HashMap<String, CartPosition>();
		this.currency = Currency.getInstance("EUR");
	}

	@Override
	public CartPosition addCartPosition(CartPosition cartPosition) {
		return cartPositionsById.put(cartPosition.getCartItem().getId(), cartPosition);
	}

	@Override
	public CartPosition removeCartPosition(CartPosition cartPosition) {
		return cartPositionsById.remove(cartPosition.getCartItem().getId());
	}

	@Override
	public List<CartPosition> getCartPositions() {
		return new ArrayList<CartPosition>(cartPositionsById.values());
	}

	@Override
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	@Override
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Override
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	@Override
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public boolean containsCartPosition(CartItem cartItem) {
		return cartPositionsById.containsKey(cartItem.getId());
	}

	@Override
	public CartPosition getCartPosition(CartItem cartItem) {
		return cartPositionsById.get(cartItem.getId());
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}

	@Override
	public Price getTotalPrice() {
		return totalPrice;
	}

	@Override
	public void setTotalPrice(Price totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public Payment getPayment() {
		return payment;
	}

	@Override
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@Override
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

}
