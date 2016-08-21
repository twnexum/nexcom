package javax.commerce.frontend.controller;

import java.util.List;

import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.storefront.StorefrontPosition;
import javax.commerce.frontend.services.InventoryService;
import javax.commerce.frontend.services.ShoppingCartService;
import javax.commerce.frontend.services.StorefrontService;
import javax.commerce.frontend.settings.StorefrontState;
import javax.commerce.frontend.settings.StorefrontViewState;

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
public class StorefrontController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private StorefrontState storefrontState;
	
	@Autowired
	private StorefrontService storefrontService;
	
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping(value = "/storefront", method = RequestMethod.GET)
	public String listInventory(ModelMap model, @RequestParam(value = "compactView", required = false) Boolean compactView) {
		
		if (compactView != null) {
			if (compactView.booleanValue() == true) {
				storefrontState.setViewSetting(StorefrontViewState.COMPACT);
			} else {
				storefrontState.setViewSetting(StorefrontViewState.DETAILED);
			}
		}

		Boolean listCompact = Boolean.valueOf(storefrontState.getViewSetting().equals(StorefrontViewState.COMPACT));
		model.addAttribute("isCompactView", listCompact);
		
		List<InventoryPosition> inventoryPositions = inventoryService.getInventoryPositions(listCompact);		
		List<StorefrontPosition> storefrontPositions = storefrontService.getStorefrontPositions(inventoryPositions);
		model.addAttribute("storefrontPositions", storefrontPositions);
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);		
		
		return "storefront";
	}
	
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public String addToCart(ModelMap model, @RequestParam(value = "cartItemId", required = true) String cartItemId, @RequestParam(value = "quantity", required = true) Integer quantity) {

    	if (quantity > 0) {
    	
    		CartItem cartItem = (CartItem) repositoryService.findCartItemById(cartItemId);
    		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(cartItemId);
    		if (inventoryPosition != null && inventoryPosition.getAvailableQuantity() >= quantity) {
    		
        		shoppingCartService.addToCart(shoppingCart, cartItem, quantity);
    		}
    	}
    	
		Boolean listCompact = Boolean.valueOf(storefrontState.getViewSetting().equals(StorefrontViewState.COMPACT));
		model.addAttribute("isCompactView", listCompact);
		
		List<InventoryPosition> inventoryPositions = inventoryService.getInventoryPositions(listCompact);		
		List<StorefrontPosition> storefrontPositions = storefrontService.getStorefrontPositions(inventoryPositions);
		model.addAttribute("storefrontPositions", storefrontPositions);
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);		
    	
		return "storefront";
	}

}
