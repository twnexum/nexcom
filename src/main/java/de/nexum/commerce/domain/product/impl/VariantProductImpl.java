package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantProductImpl extends ProductImpl implements VariantProduct {

	private Set<Variant> variants;
	private Set<String> variantAttributeTuple;
	
	public VariantProductImpl(Set<Attribute> attributes, Price price, Set<Variant> variants, Set<String> variantAttributeTuple) {
		this(UUID.randomUUID().toString(), attributes, price, variants, variantAttributeTuple);
	}
	
	public VariantProductImpl(String id, Set<Attribute> attributes, Price price, Set<Variant> variants, Set<String> variantAttributeTuple) {
		super(id, attributes, price);
		this.variants = variants;
		this.variantAttributeTuple = variantAttributeTuple;
	}
	
	@Override
	public Set<Variant> getVariants() {
		return Collections.unmodifiableSet(variants);
	}
	
	@Override
	public Price getPrice() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> getVariantAttributeTuple() {
		return Collections.unmodifiableSet(variantAttributeTuple);
	}
	
	@Override
	public Boolean isVariantProduct() {
		return Boolean.TRUE;
	}	

}
