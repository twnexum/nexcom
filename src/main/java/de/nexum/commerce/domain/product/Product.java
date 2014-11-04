package de.nexum.commerce.domain.product;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import de.nexum.commerce.domain.patterns.CartItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="productCollection")
@TypeAlias("product")
public interface Product extends CartItem {
	
	boolean isVariantProduct();

}
