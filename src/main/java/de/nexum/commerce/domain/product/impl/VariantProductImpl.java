package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Set;

import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantProductImpl extends ProductImpl implements VariantProduct {

	private Set<Variant> variants;
	private Set<String> variantAttributeTuple;

	@Override
	public Set<Variant> getVariants() {
		return Collections.unmodifiableSet(variants);
	}

	public void setVariants(Set<Variant> variants) {
		this.variants = variants;
	}
	
	@Override
	public Price getPrice() {
		throw new UnsupportedOperationException();
	}

	public void setPrice(Price price) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> getVariantAttributeTuple() {
		return Collections.unmodifiableSet(variantAttributeTuple);
	}

	public void setVariantAttributeTuple(Set<String> variantAttributeTuple) {
		this.variantAttributeTuple = variantAttributeTuple;
	}
	
	@Override
	public boolean isVariantProduct() {
		return true;
	}	

}
