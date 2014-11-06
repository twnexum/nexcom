package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantImpl extends AbstractIdentifiableItemImpl implements Variant {

	private Set<Attribute> attributes;
	private Price price;
	private VariantProduct product;
	
	public VariantImpl(VariantProduct product, Set<Attribute> attributes, Price price) {
		this(UUID.randomUUID().toString(), product, attributes, price);
	}
	
	public VariantImpl(String id, VariantProduct product, Set<Attribute> attributes, Price price) {
		super(id);
		this.product = product;
		this.attributes = attributes;
		this.price = price;
	}

	@Override
	public Price getPrice() {
		return price;
	}

	@Override
	public Set<Attribute> getAttributes() {
		return Collections.unmodifiableSet(attributes);
	}

	@Override
	public VariantProduct getProduct() {
		return product;
	}

}
