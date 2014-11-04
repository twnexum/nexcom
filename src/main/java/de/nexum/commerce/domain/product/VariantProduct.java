package de.nexum.commerce.domain.product;

import java.util.Set;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="productCollection")
@TypeAlias("variantProduct")
public interface VariantProduct extends Product {
	
	Set<Variant> getVariants();
	
	Set<String> getVariantAttributeTuple();
	
}
