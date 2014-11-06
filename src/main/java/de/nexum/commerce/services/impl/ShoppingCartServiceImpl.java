package de.nexum.commerce.services.impl;

import java.math.BigDecimal;
import java.util.Currency;

import org.springframework.stereotype.Service;

import de.nexum.commerce.domain.cart.CartPosition;
import de.nexum.commerce.domain.cart.Discount;
import de.nexum.commerce.domain.cart.Shipping;
import de.nexum.commerce.domain.cart.ShoppingCart;
import de.nexum.commerce.domain.cart.impl.CartPositionImpl;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.impl.PriceImpl;
import de.nexum.commerce.services.ShoppingCartService;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Override
	public Price calculateTotalAmount(ShoppingCart shoppingCart) {

		BigDecimal shoppingCartTotalAmount = BigDecimal.valueOf(0.0);
		Currency currency = shoppingCart.getCurrency();
		
		for (CartPosition nextCartPosition : shoppingCart.getCartPositions()) {
			
			CartItem cartItem = nextCartPosition.getCartItem();
			
			BigDecimal itemAmount = cartItem.getPrice().getAmount();
			BigDecimal cartPositionTotalAmount = itemAmount.multiply(BigDecimal.valueOf(nextCartPosition.getQuantity()));
			
			Price cartPositionTotalPrice = new PriceImpl(null, cartPositionTotalAmount, currency);
			nextCartPosition.setTotalPrice(cartPositionTotalPrice);
			
			if (Product.class.isAssignableFrom(cartItem.getClass()) 
					|| Variant.class.isAssignableFrom(cartItem.getClass()) 
					|| Shipping.class.isAssignableFrom(cartItem.getClass())) {
				
				shoppingCartTotalAmount = shoppingCartTotalAmount.add(cartPositionTotalAmount);
			} else if (Discount.class.isAssignableFrom(cartItem.getClass())) {
				
				shoppingCartTotalAmount = shoppingCartTotalAmount.subtract(cartPositionTotalAmount);
			}			
		}
		
		Price shoppingCartTotalPrice = new PriceImpl(null, shoppingCartTotalAmount, currency);
		shoppingCart.setTotalPrice(shoppingCartTotalPrice);
		
		return shoppingCartTotalPrice;
	}

	@Override
	public CartPosition addToCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity) {

		CartPosition cartPosition = null;
		if (shoppingCart.containsCartPosition(cartItem)) {
			
			cartPosition = shoppingCart.getCartPosition(cartItem);
			cartPosition.incrementQuantity(quantity);
		} else {
			
			cartPosition = new CartPositionImpl(cartItem, quantity);
			shoppingCart.addCartPosition(cartPosition);
		}
		
		return cartPosition;
	}

	@Override
	public CartPosition removeFromCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity) {

		CartPosition cartPosition = null;
		if (shoppingCart.containsCartPosition(cartItem)) {
			
			cartPosition = shoppingCart.getCartPosition(cartItem);
			cartPosition.decrementQuantity(quantity);
			
			if (cartPosition.getQuantity() <= 0) {
				
				shoppingCart.removeCartPosition(cartPosition);
			}
		}
		
		return cartPosition;
	}

}
