package javax.commerce.frontend.services.impl;

import java.math.BigDecimal;
import java.util.Currency;

import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.cart.Discount;
import javax.commerce.domain.cart.Shipping;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.cart.impl.CartPositionImpl;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.impl.PriceImpl;
import javax.commerce.frontend.services.ShoppingCartService;

import org.springframework.stereotype.Service;

/**
 * @author Thomas Weckert
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
		
		Price shoppingCartTotalPrice = new PriceImpl(shoppingCart.getId(), shoppingCartTotalAmount, currency);
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
