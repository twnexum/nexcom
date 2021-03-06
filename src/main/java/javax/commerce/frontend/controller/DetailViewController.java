package javax.commerce.frontend.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.cart.ShoppingCart;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.VariantProduct;
import javax.commerce.frontend.services.InventoryService;
import javax.commerce.frontend.services.ShoppingCartService;
import javax.commerce.frontend.services.StorefrontService;
import javax.commerce.frontend.settings.StorefrontState;
import javax.commerce.util.AttributeUtils;

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
	private RepositoryService repositoryService;
	
	@Autowired
	private StorefrontState storefrontState;

	@RequestMapping(value = "/detailView", method = RequestMethod.GET)
	public String detailView(ModelMap model, @RequestParam(value = "id", required = true) String cartItemId) {
		
		CartItem cartItem = repositoryService.findCartItemById(cartItemId);		
		model.addAttribute("cartItem", cartItem);
		
		if (VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
			
			VariantProduct variantProduct = (VariantProduct) cartItem;
			List<String> variantAttributeTuple = new ArrayList<String>(variantProduct.getVariantAttributeTuple());
			Collections.sort(variantAttributeTuple);
			
			Map<String, String> variantSelectionsById = new HashMap<String, String>();					
			Map<String, String> variantImagesById = new HashMap<String, String>();
			
			for (Variant nextVariant : variantProduct.getVariants()) {
				
				Map<String, String> variantAttributesMap = AttributeUtils.asMap(nextVariant.getAttributes());
				
				StringBuffer buf = new StringBuffer();						
				for (String nextVariantAttribute : variantAttributeTuple) {
					
					if (buf.length() > 0) {
						buf.append(" / ");
					}
					
					buf.append(variantAttributesMap.get(nextVariantAttribute));
				}
				
				buf.append(" : ").append(nextVariant.getPrice());
				
				variantSelectionsById.put(nextVariant.getId(), buf.toString());
				variantImagesById.put(nextVariant.getId(), variantAttributesMap.get("image"));
			}
			
			model.addAttribute("variantSelectionsById", variantSelectionsById);
			model.addAttribute("variantImagesById", variantImagesById);
			model.addAttribute("isVariantProduct", Boolean.TRUE);
		}
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);	
		
		return "detailView";
	}
	
    @RequestMapping(value = "/detailViewAddToCart", method = RequestMethod.POST)
	public String addToCart(ModelMap model, @RequestParam(value = "cartItemId", required = true) String cartItemId, @RequestParam(value = "quantity", required = true) Integer quantity) {

    	CartItem cartItem = (CartItem) repositoryService.findCartItemById(cartItemId);
    	model.addAttribute("cartItem", cartItem);	
    	
		// all model attributes are considered to be exposed as URI template variables in redirect URLs
		// thus, we add an "id" variable to the model in order to return to the previous detail view
		if (Variant.class.isAssignableFrom(cartItem.getClass())) {
			Variant variant = (Variant) cartItem;
			model.addAttribute("id", variant.getProductId());	
		} else if (Product.class.isAssignableFrom(cartItem.getClass())) {
			model.addAttribute("id", cartItem.getId());	
		}
    	
		InventoryPosition inventoryPosition = inventoryService.findInventoryByCartItemId(cartItemId);
		if (inventoryPosition != null) {
						
			if (quantity > 0 && inventoryPosition.getAvailableQuantity() >= quantity) {	        		
				shoppingCartService.addToCart(shoppingCart, cartItem, quantity);
			}
		}
		
		Price shoppingCartTotalAmount = shoppingCartService.calculateTotalAmount(shoppingCart);
		model.addAttribute("shoppingCartTotalAmount", shoppingCartTotalAmount);
		
		Integer shoppingCartPositionSize = Integer.valueOf(shoppingCart.getCartPositions().size());
		model.addAttribute("shoppingCartPositionSize", shoppingCartPositionSize);				
		
		return "redirect:detailView";
	}	
	
}
