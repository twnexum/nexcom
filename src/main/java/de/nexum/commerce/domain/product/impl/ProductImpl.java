package de.nexum.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Map;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Document(collection="productCollection")
@TypeAlias("product")
public class ProductImpl extends AbstractIdentifiableItemImpl implements Product {
	
	private Map<String, String> attributes;
	private Price price;
	private boolean isVariantProduct;
	
	public ProductImpl(String id, Map<String, String> attributes, Price price) {
		
		super(id);
		
		this.attributes = attributes;
		this.price = price;
		this.isVariantProduct = false;
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
		return isVariantProduct;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			return false;
		}
		
		if (!ProductImpl.class.isAssignableFrom(obj.getClass())) {
            return false;
		}
        
		if (obj == this) {
            return true;
		}
		
		ProductImpl otherProduct = (ProductImpl) obj;
		return otherProduct.getId().equals(this.getId())
				&& otherProduct.getPrice().equals(this.getPrice())
				&& otherProduct.getAttributes().equals(this.getAttributes());
	}

}
