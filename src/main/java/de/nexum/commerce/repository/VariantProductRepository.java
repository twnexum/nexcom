package de.nexum.commerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public interface VariantProductRepository extends PagingAndSortingRepository<VariantProduct, String> {

	// intentionally left blank
	
}
