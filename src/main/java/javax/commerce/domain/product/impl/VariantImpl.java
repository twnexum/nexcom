package javax.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Variant;
import javax.commerce.util.AttributeUtils;

/**
 * @author Thomas Weckert
 */
public class VariantImpl extends AbstractIdentifiableItemImpl implements Variant {

	private static final long serialVersionUID = -5861227913754961679L;
	
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
	
	@Override
	public Map<String, String> getAttributesMap() {		
		return AttributeUtils.asMap(getAttributes());
	}

}
