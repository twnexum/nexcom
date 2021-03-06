package javax.commerce.domain.product.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import javax.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import javax.commerce.domain.product.Attribute;
import javax.commerce.domain.product.Price;
import javax.commerce.domain.product.Product;
import javax.commerce.util.AttributeUtils;

/**
 * @author Thomas Weckert
 */
public class ProductImpl extends AbstractIdentifiableItemImpl implements Product {

	private static final long serialVersionUID = -5883145797480118025L;
	
	private Set<Attribute> attributes;
	private Price price;
	
	public ProductImpl(String id, Set<Attribute> attributes, Price price) {		
		super(id);		
		this.attributes = attributes;		
		this.price = price;
	}
	
	@Override
	public Set<Attribute> getAttributes() {
		return Collections.unmodifiableSet(attributes);
	}

	@Override
	public Price getPrice() {
		return price;
	}

	@Override
	public Boolean isVariantProduct() {
		return Boolean.FALSE;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (Product.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		Product otherProduct = (Product) obj;
		return otherProduct.isVariantProduct().equals(this.isVariantProduct())
				&& otherProduct.getPrice().equals(this.getPrice())
				&& otherProduct.getAttributes().equals(this.getAttributes());
	}

	@Override
	public Map<String, String> getAttributesMap() {		
		return AttributeUtils.asMap(getAttributes());
	}

}
