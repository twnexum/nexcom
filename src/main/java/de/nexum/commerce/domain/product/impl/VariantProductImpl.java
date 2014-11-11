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

	private static final long serialVersionUID = 4540711182949305003L;
	
	private Set<Variant> variants;
	private Set<String> variantAttributeTuple;
	
	public VariantProductImpl(Set<Attribute> attributes, Price price, Set<Variant> variants, Set<String> variantAttributeTuple) {
		this(UUID.randomUUID().toString(), attributes, variants, variantAttributeTuple);
	}
	
	public VariantProductImpl(String id, Set<Attribute> attributes, Set<Variant> variants, Set<String> variantAttributeTuple) {
		super(id, attributes, null);
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (VariantProduct.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		VariantProduct otherVariantProduct = (VariantProduct) obj;
		return otherVariantProduct.isVariantProduct().equals(this.isVariantProduct())
				&& otherVariantProduct.getAttributes().equals(this.getAttributes())
				&& otherVariantProduct.getVariants().equals(this.getVariants())
				&& otherVariantProduct.getVariantAttributeTuple().equals(this.getVariantAttributeTuple());			
	}

}
