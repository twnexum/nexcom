package javax.commerce.domain.product;

import java.util.Set;

/**
 * @author Thomas Weckert
 */
public interface VariantProduct extends Product {
	
	Set<Variant> getVariants();
	
	Set<String> getVariantAttributeTuple();
	
}
