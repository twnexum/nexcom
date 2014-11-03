package de.nexum.commerce.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.domain.storefront.StorefrontPosition;
import de.nexum.commerce.domain.storefront.impl.StorefrontPositionImpl;
import de.nexum.commerce.services.StorefrontService;
import de.nexum.commerce.util.CartItemPriceComparator;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Service
public class StorefrontServiceImpl implements StorefrontService {

	@Override
	public List<StorefrontPosition> getStorefrontPositions(List<InventoryPosition> inventoryPositions) {

		List<StorefrontPosition> storefrontPositions = new ArrayList<StorefrontPosition>();
		
		for (InventoryPosition nextInventoryPosition : inventoryPositions) {
			
			CartItem cartItem = nextInventoryPosition.getCartItem();
			
			StorefrontPositionImpl storefrontPosition = new StorefrontPositionImpl();
			storefrontPosition.setCartItem(cartItem);
			
			if (Product.class.isAssignableFrom(cartItem.getClass()) && !VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
				
				storefrontPosition.setPrice(cartItem.getPrice());
			} else if (Variant.class.isAssignableFrom(cartItem.getClass())) {
				
				storefrontPosition.setPrice(cartItem.getPrice());
			} else if (VariantProduct.class.isAssignableFrom(cartItem.getClass())) {
				
				VariantProduct variantProduct = (VariantProduct) cartItem;
				List<Variant> variants = new ArrayList<Variant>(variantProduct.getVariants());
				Collections.sort(variants, new CartItemPriceComparator(true));
				
				storefrontPosition.setPrice(variants.get(0).getPrice());
			}
			
			storefrontPositions.add(storefrontPosition);
		}
		
		return storefrontPositions;
	}

}
