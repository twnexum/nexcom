package de.nexum.commerce.domain.product.impl;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.DBRef;

import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantImpl extends AbstractIdentifiableItemImpl implements Variant {

	private Map<String, String> attributes;
	private Price price;	
	@DBRef private VariantProduct product;
	
	public VariantImpl(String id, VariantProduct product, Price price, Map<String, String> attributes) {
		
		super(id);
		
		this.product = product;
		this.price = price;
		this.attributes = attributes;
	}

	@Override
	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public VariantProduct getProduct() {
		return product;
	}

	public void setProduct(VariantProduct product) {
		this.product = product;
	}

}
