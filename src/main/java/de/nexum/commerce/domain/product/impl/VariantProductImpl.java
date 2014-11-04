package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="productCollection")
@TypeAlias("variantProduct")
public class VariantProductImpl extends ProductImpl implements VariantProduct {

	private Set<Variant> variants;
	private Set<String> variantAttributeTuple;
	private boolean isVariantProduct;
	
	public VariantProductImpl(String id, Map<String, String> attributes, Price price, Set<Variant> variants, Set<String> variantAttributeTuple) {
		
		super(id, attributes, price);
		
		this.variants = variants;
		this.variantAttributeTuple = variantAttributeTuple;
		this.isVariantProduct = true;
	}

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
		return isVariantProduct;
	}	

}
