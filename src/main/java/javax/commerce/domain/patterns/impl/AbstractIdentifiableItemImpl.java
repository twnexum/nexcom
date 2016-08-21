package javax.commerce.domain.patterns.impl;

import javax.commerce.domain.patterns.IdentifiableItem;

/**
 * @author Thomas Weckert
 */
public abstract class AbstractIdentifiableItemImpl implements IdentifiableItem {

	private static final long serialVersionUID = 4165762970117504974L;
	
	private String id;

	public AbstractIdentifiableItemImpl(String id) {
		super();
		this.id = id.trim();
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