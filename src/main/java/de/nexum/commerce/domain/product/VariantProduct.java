package de.nexum.commerce.domain.product;

import java.util.Set;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface VariantProduct extends Product {
	
	Set<Variant> getVariants();
	
	Set<String> getVariantAttributeTuple();
	
}
