package de.nexum.commerce.domain.patterns.impl;

import de.nexum.commerce.domain.patterns.IdentifiableItem;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public abstract class AbstractIdentifiableItemImpl implements IdentifiableItem {
	
	private String id;

	public AbstractIdentifiableItemImpl(String id) {
		super();
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean isIdentical(IdentifiableItem identifiableItem) {
		return this.getId().equals(identifiableItem.getId());
	}
	
}