package javax.commerce.backend.services.impl;

import java.util.List;

import javax.commerce.backend.dao.GenericDAO;
import javax.commerce.backend.dao.HelperDAO;
import javax.commerce.backend.services.RepositoryService;
import javax.commerce.domain.inventory.InventoryPosition;
import javax.commerce.domain.patterns.CartItem;
import javax.commerce.domain.product.Product;
import javax.commerce.domain.product.Variant;
import javax.commerce.domain.product.VariantProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Thomas Weckert
 */
@Repository
public class RepositoryServiceImpl implements RepositoryService {

	@Autowired
	private GenericDAO<Product> productDAO;
	
	@Autowired
	private GenericDAO<VariantProduct> variantProductDAO;
	
	@Autowired
	private GenericDAO<InventoryPosition> inventoryDAO;
	
	@Autowired
	private GenericDAO<Variant> variantDAO;
	
	@Autowired
	private HelperDAO helperDAO;

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "products", allEntries=true)
	public void saveProduct(Product product) {
		if (product.isVariantProduct()) {
			variantProductDAO.save((VariantProduct) product);
		} else {
			productDAO.save(product);
		}
	}

	@Override
	@Transactional(readOnly = true)
	@Cacheable("products")
	public CartItem findCartItemById(String cartItemId) {
		if (helperDAO.isVariant(cartItemId)) {
			return variantDAO.findByID(cartItemId);
		} else if (helperDAO.isVariantProduct(cartItemId)) {
			return variantProductDAO.findByID(cartItemId);
		} else {
			return productDAO.findByID(cartItemId);
		}
	}

	@Override
	@Transactional(readOnly = false)
	@CacheEvict(value = "inventories", allEntries=true)
	public void saveInventory(InventoryPosition inventoryPosition) {
		inventoryDAO.save(inventoryPosition);		
	}
	
	@Override
	@Transactional(readOnly = true)
	@Cacheable("inventories")
	public InventoryPosition findInventoryByProductId(String productID) {
		return inventoryDAO.findByID(productID);
	}	

	@Override
	public List<InventoryPosition> findAllInventories() {
		return (List<InventoryPosition>) inventoryDAO.findAll();
	}

}
