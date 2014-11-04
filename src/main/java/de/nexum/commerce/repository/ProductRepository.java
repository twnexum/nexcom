package de.nexum.commerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import de.nexum.commerce.domain.product.Product;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	// intentionally left blank
	
}
