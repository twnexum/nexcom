package de.nexum.commerce.services;

import de.nexum.commerce.domain.cart.CartPosition;
import de.nexum.commerce.domain.cart.ShoppingCart;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;

public interface ShoppingCartService {
	
	CartPosition addToCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity);
	
	CartPosition removeFromCart(ShoppingCart shoppingCart, CartItem cartItem, Integer quantity);

	Price calculateTotalAmount(ShoppingCart shoppingCart);
	
}
