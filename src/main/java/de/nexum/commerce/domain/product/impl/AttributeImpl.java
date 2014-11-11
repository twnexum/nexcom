package de.nexum.commerce.domain.product.impl;

import java.util.UUID;

import de.nexum.commerce.domain.patterns.impl.AbstractIdentifiableItemImpl;
import de.nexum.commerce.domain.product.Attribute;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class AttributeImpl extends AbstractIdentifiableItemImpl implements Attribute {

	private static final long serialVersionUID = 5964015155543469945L;
	
	private String key;
	private String value;
	private String productId;
	
	public AttributeImpl(String productId, String key, String value) {
		this(UUID.randomUUID().toString(), productId, key, value);
	}
	
	public AttributeImpl(String id, String productId, String key, String value) {		
		super(id);		
		this.productId = productId;
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (Attribute.class.isAssignableFrom(obj.getClass()) == false) {
			return false;
		}

		Attribute otherProductAttribute = (Attribute) obj;
		return otherProductAttribute.getKey().equalsIgnoreCase(this.getKey())
				&& otherProductAttribute.getValue().equals(this.getValue());
	}
	
	@Override
	public String getProductId() {
		return productId;
	}
	
	void setProductId(String productId) {
		this.productId = productId;
	}

}
