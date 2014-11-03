package de.nexum.commerce.domain.product.impl;

import java.util.Map;

import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantImpl implements Variant {

	private String id;	
	private Map<String, String> attributes;
	private Price price;
	private VariantProduct product;
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
