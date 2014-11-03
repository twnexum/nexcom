package de.nexum.commerce.frontend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.nexum.commerce.domain.cart.ShoppingCart;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.domain.storefront.StorefrontPosition;
import de.nexum.commerce.frontend.settings.StorefrontState;
import de.nexum.commerce.services.InventoryService;
import de.nexum.commerce.services.ShoppingCartService;
import de.nexum.commerce.services.StorefrontService;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Controller
public class DetailViewController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private StorefrontService storefrontService;
	
	@Autowired
	private StorefrontState storefrontState;

	@RequestMapping(value = "/detailView", method = RequestMethod.GET)
	public String detailView(ModelMap model, @RequestParam(value = "id", required = true) String cartItemId) {
	
		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(cartItemId);
		if (inventoryPosition != null) {
			
			List<StorefrontPosition> storefrontPositions = storefrontService.getStorefrontPositions(Collections.singletonList(inventoryPosition));
			
			StorefrontPosition storefrontPosition = storefrontPositions.get(0);
			if (storefrontPosition != null) {
				
				model.addAttribute("storefrontPosition", storefrontPosition);
				
				if (VariantProduct.class.isAssignableFrom(inventoryPosition.getCartItem().getClass())) {
					
					VariantProduct variantProduct = (VariantProduct) inventoryPosition.getCartItem();
					List<String> variantAttributeTuple = new ArrayList<String>(variantProduct.getVariantAttributeTuple());
					Collections.sort(variantAttributeTuple);
					
					Map<String, String> variantSelectionsById = new HashMap<String, String>();					
					Map<String, String> variantImagesById = new HashMap<String, String>();
					
					for (Variant nextVariant : variantProduct.getVariants()) {
						
						StringBuffer buf = new StringBuffer();						
						for (String nextVariantAttribute : variantAttributeTuple) {
							
							if (buf.length() > 0) {
								buf.append(" / ");
							}
							
							buf.append(nextVariant.getAttributes().get(nextVariantAttribute));
						}
						
						buf.append(" : ").append(nextVariant.getPrice());
						
						variantSelectionsById.put(nextVariant.getId(), buf.toString());
						variantImagesById.put(nextVariant.getId(), nextVariant.getAttributes().get("image"));
					}
					
					model.addAttribute("variantSelectionsById", variantSelectionsById);
					model.addAttribute("variantImagesById", variantImagesById);
					model.addAttribute("isVariantProduct", Boolean.TRUE);
				}
			}
		}
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);	
		
		return "detailView";
	}
	
    @RequestMapping(value = "/detailViewAddToCart", method = RequestMethod.POST)
	public String addToCart(ModelMap model, @RequestParam(value = "cartItemId", required = true) String cartItemId, @RequestParam(value = "quantity", required = true) Integer quantity) {

		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(cartItemId);
		if (inventoryPosition != null) {
			
			List<StorefrontPosition> storefrontPositions = storefrontService.getStorefrontPositions(Collections.singletonList(inventoryPosition));
			
			StorefrontPosition storefrontPosition = storefrontPositions.get(0);
			if (storefrontPosition != null) {
				model.addAttribute("storefrontPosition", storefrontPosition);
			}
			
			if (quantity > 0 && inventoryPosition.getAvailableQuantity() >= quantity) {	        		
				shoppingCartService.addToCart(shoppingCart, inventoryPosition.getCartItem(), quantity);
			}
			
			// all model attributes are considered to be exposed as URI template variables in redirect URLs
			// thus, we add an "id" variable to the model in order to return to the previous detail view
			if (Variant.class.isAssignableFrom(inventoryPosition.getCartItem().getClass())) {
				
				Variant variant = (Variant) inventoryPosition.getCartItem();
				model.addAttribute("id", variant.getProduct().getId());	
			} else if (Product.class.isAssignableFrom(inventoryPosition.getCartItem().getClass())) {
				
				Product product = (Product) inventoryPosition.getCartItem();
				model.addAttribute("id", product.getId());	
			}
		}
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);				
		
		return "redirect:detailView";
	}	
	
}
