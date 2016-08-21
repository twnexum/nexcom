package javax.commerce.domain.patterns;

import java.io.Serializable;


/**
 * @author Thomas Weckert
 */
public interface IdentifiableItem extends Serializable {

	String getId();
	
	boolean isIdentical(IdentifiableItem identifiableItem);
	
}
