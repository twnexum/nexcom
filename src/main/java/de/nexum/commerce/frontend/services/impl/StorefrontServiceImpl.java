package de.nexum.commerce.frontend.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.nexum.commerce.backend.services.RepositoryService;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.domain.storefront.StorefrontPosition;
import de.nexum.commerce.domain.storefront.impl.StorefrontPositionImpl;
import de.nexum.commerce.frontend.services.StorefrontService;
import de.nexum.commerce.util.CartItemPriceComparator;
import de.nexum.commerce.util.StorefrontPositionCartItemAttributeComparator;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
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
