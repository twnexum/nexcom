package de.nexum.commerce.domain.patterns.impl;

import org.springframework.data.annotation.Id;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public abstract class AbstractIdentifiableItemImpl implements IdentifiableItem {

	@Id
	private String id;
	
	public AbstractIdentifiableItemImpl(String id) {
		
		super();
		
		this.id = id;
	}
	
	@Override
	public String getId() {
		return id;
	}

}
