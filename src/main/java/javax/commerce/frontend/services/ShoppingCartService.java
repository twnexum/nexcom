package javax.commerce.frontend.services;

import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;

public interface ShoppingCartService {
	
	CartPosition addToCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity);
	
	CartPosition removeFromCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity);

	Price calculateTotalAmount(ShoppingCart shoppingCart);
	
}
