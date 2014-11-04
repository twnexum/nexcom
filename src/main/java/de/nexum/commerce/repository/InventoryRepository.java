package de.nexum.commerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import de.nexum.commerce.domain.inventory.InventoryPosition;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Repository
public interface InventoryRepository extends PagingAndSortingRepository<InventoryPosition, String>{

	// intentionally left blank
	
}
