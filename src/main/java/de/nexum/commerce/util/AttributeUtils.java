package de.nexum.commerce.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.nexum.commerce.domain.product.Attribute;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class AttributeUtils {

	private AttributeUtils() {
		// intentionally left blank
	}
	
	public static Map<String, String> asMap(Set<Attribute> attributesSet) {
		
		Map<String, String> attributesMap = new HashMap<String, String>();
		for (Attribute nextAttribute : attributesSet) {
			attributesMap.put(nextAttribute.getKey(), nextAttribute.getValue());
		}
		
		return attributesMap;
	}
	
}
