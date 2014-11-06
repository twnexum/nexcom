package de.nexum.commerce.manager.impl;

import org.springframework.transaction.annotation.Transactional;

import de.nexum.commerce.dao.GenericDAO;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.manager.ProductManager;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
public class ProductManagerImpl implements ProductManager {

	private GenericDAO<Product> productDAO;

	@Override
	@Transactional
	public void insertProduct(Product product) {
		productDAO.save(product);
	}

	@Override
	public Product findByID(String productID) {
		return productDAO.findByID(productID);
	}

	public void setProductDAO(GenericDAO<Product> productDAO) {
		this.productDAO = productDAO;
	}

}
