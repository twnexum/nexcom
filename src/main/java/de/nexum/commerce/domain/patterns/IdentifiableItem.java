package de.nexum.commerce.domain.patterns;


/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface IdentifiableItem {

	String getId();
	
	boolean isIdentical(IdentifiableItem identifiableItem);
	
}
