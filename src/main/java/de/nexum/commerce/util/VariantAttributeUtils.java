package de.nexum.commerce.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class VariantAttributeUtils {
	
	private VariantAttributeUtils() {
		// intentionally left blank
	}
	
	public static String[] asArray(Set<String> variantAttributeTuple) {
		return variantAttributeTuple.toArray(new String[variantAttributeTuple.size()]);
	}
	
	public static Set<String> asSet(String[] variantAttributeTuple) {
	
		Set<String> set = new HashSet<String>();
		Collections.addAll(set, variantAttributeTuple);
		
		return set;
	}

}
