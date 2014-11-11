package de.nexum.commerce.domain.patterns;

import java.io.Serializable;


/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface IdentifiableItem extends Serializable {

	String getId();
	
	boolean isIdentical(IdentifiableItem identifiableItem);
	
}
