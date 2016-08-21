package javax.commerce.frontend.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.VariantProduct;
import javax.commerce.domain.storefront.StorefrontPosition;
import javax.commerce.domain.storefront.impl.StorefrontPositionImpl;
import javax.commerce.frontend.services.StorefrontService;
import javax.commerce.util.CartItemPriceComparator;
import javax.commerce.util.StorefrontPositionCartItemAttributeComparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thomas Weckert
 */
@Service
public class StorefrontServiceImpl implements StorefrontService {
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	public List<StorefrontPosition> getStorefrontPositions(List<InventoryPosition> inventoryPositions) {

		List<StorefrontPosition> storefrontPositions = new ArrayList<StorefrontPosition>();
		
		for (InventoryPosition nextInventoryPosition : inventoryPositions) {
			
			CartItem cartItem = repositoryService.findCartItemById(nextInventoryPosition.getProductId());
			Price price = null;
			
			if (Product.class.isAssignableFrom(cartItem.getClass()) && !VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
				
				price = cartItem.getPrice();
			} else if (Variant.class.isAssignableFrom(cartItem.getClass())) {
				
				price = cartItem.getPrice();
			} else if (VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
				
				// this is a product with variants- take the lowest price as a "starter"...
				
				VariantProduct variantProduct = (VariantProduct) cartItem;
				List<Variant> variants = new ArrayList<Variant>(variantProduct.getVariants());
				Collections.sort(variants, new CartItemPriceComparator(true));
				
				price = variants.get(0).getPrice();
			}
			
			storefrontPositions.add(new StorefrontPositionImpl(cartItem, price));
		}
		
		Collections.sort(storefrontPositions, new StorefrontPositionCartItemAttributeComparator("title"));
		
		return storefrontPositions;
	}

}
