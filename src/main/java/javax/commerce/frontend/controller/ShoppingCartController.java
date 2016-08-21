package javax.commerce.frontend.controller;

import java.util.Collections;
import java.util.List;

import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.cart.CartPosition;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.frontend.services.InventoryService;
import javax.commerce.frontend.services.ShoppingCartService;
import javax.commerce.util.CartPositionCartItemAttributeComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Thomas Weckert
 */
@Controller
public class ShoppingCartController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(value = "/showCart", method = RequestMethod.GET)
	public String showCart(ModelMap model) {
		
		List<CartPosition> cartPositions = shoppingCart.getCartPositions();
		Collections.sort(cartPositions, new CartPositionCartItemAttributeComparator("title"));
		model.addAttribute("cartPositions", cartPositions);
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);
		
		return "cart";
	}
	
    @RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
	public String removeFromCart(ModelMap model, @RequestParam(value = "productId", required = true) String productId) {

    	CartItem cartItem = (CartItem) repositoryService.findCartItemById(productId);
		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(productId);
		if (inventoryPosition != null) {
		
			shoppingCartService.removeFromCart(shoppingCart, cartItem, Integer.MAX_VALUE);
		}
		
		List<CartPosition> cartPositions = shoppingCart.getCartPositions();
		Collections.sort(cartPositions, new CartPositionCartItemAttributeComparator("title"));
		model.addAttribute("cartPositions", cartPositions);
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);		
    	
		return "cart";
	}	
    
    @RequestMapping(value = "/updateQuantity", method = RequestMethod.POST)
	public String updateQuantity(ModelMap model, @RequestParam(value = "productId", required = true) String productId, @RequestParam(value = "quantity", required = true) Integer quantity) {

    	if (quantity > 0) {
    	
    		CartItem cartItem = (CartItem) repositoryService.findCartItemById(productId);
    		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(productId);
    		if (inventoryPosition != null) {
    			
    			if (shoppingCart.containsCartPosition(cartItem) && inventoryPosition.getAvailableQuantity() >= quantity) {
    				
    				CartPosition cartPosition = shoppingCart.getCartPosition(cartItem);
    				if (cartPosition.getQuantity() < quantity) {
    					
    					Integer delta = Integer.valueOf(quantity - cartPosition.getQuantity());
    					cartPosition.incrementQuantity(delta);
    				} else if (cartPosition.getQuantity() > quantity) {
    					
    					Integer delta = Integer.valueOf(cartPosition.getQuantity() - quantity);
    					cartPosition.decrementQuantity(delta);
    				}
    			}
    		}
    	}

		List<CartPosition> cartPositions = shoppingCart.getCartPositions();
		Collections.sort(cartPositions, new CartPositionCartItemAttributeComparator("title"));
		model.addAttribute("cartPositions", cartPositions);
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);	
		
    	return "cart";
	}    
	
}
