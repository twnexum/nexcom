package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Map;

import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class ProductImpl implements Product {
	
	private String id;	
	private Map<String, String> attributes;
	private Price price;
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}
	
	public void setAttributes(Map<String, String> attributes) {
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
	public boolean isVariantProduct() {
		return false;
	}

}
