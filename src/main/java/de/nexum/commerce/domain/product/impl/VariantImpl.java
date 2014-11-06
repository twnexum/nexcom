package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantImpl extends AbstractIdentifiableItemImpl implements Variant {

	private Set<Attribute> attributes;
	private Price price;
	private String productId;
	
	public VariantImpl(String productId, Set<Attribute> attributes, Price price) {
		this(UUID.randomUUID().toString(), productId, attributes, price);
	}
	
	public VariantImpl(String id, String productId, Set<Attribute> attributes, Price price) {
		super(id);
		this.productId = productId;
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
	public String getProductId() {
		return productId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (Variant.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		Variant otherVariant = (Variant) obj;
		return otherVariant.getProductId().equals(this.getProductId())
				&& otherVariant.getPrice().equals(this.getPrice())
				&& otherVariant.getAttributes().equals(this.getAttributes());
	}

}
