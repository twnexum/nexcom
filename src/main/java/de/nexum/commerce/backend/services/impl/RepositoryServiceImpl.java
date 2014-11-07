package de.nexum.commerce.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.backend.dao.GenericDAO;
import de.nexum.commerce.backend.dao.HelperDAO;
import de.nexum.commerce.backend.services.RepositoryService;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.patterns.CartItem;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@Service
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
	@Transactional
	public void saveProduct(Product product) {
		if (product.isVariantProduct()) {
			variantProductDAO.save((VariantProduct) product);
		} else {
			productDAO.save(product);
		}
	}

	@Override
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
	@Transactional
	public void saveInventory(InventoryPosition inventoryPosition) {
		inventoryDAO.save(inventoryPosition);		
	}
	
	@Override
	public InventoryPosition findInventoryByProductId(String productID) {
		return inventoryDAO.findByID(productID);
	}	

	@Override
	public List<InventoryPosition> findAllInventories() {
		return (List<InventoryPosition>) inventoryDAO.findAll();
	}

}