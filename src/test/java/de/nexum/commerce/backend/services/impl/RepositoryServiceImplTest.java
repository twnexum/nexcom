package de.nexum.commerce.backend.services.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.nexum.commerce.backend.services.RepositoryService;
import de.nexum.commerce.domain.inventory.InventoryPosition;
import de.nexum.commerce.domain.inventory.impl.InventoryPositionImpl;
import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.Variant;
import de.nexum.commerce.domain.product.VariantProduct;
import de.nexum.commerce.domain.product.impl.AttributeImpl;
import de.nexum.commerce.domain.product.impl.PriceImpl;
import de.nexum.commerce.domain.product.impl.ProductImpl;
import de.nexum.commerce.domain.product.impl.VariantImpl;
import de.nexum.commerce.domain.product.impl.VariantProductImpl;
import de.nexum.test.AbstractTest;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@ContextConfiguration(locations = {"classpath:/test-application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RepositoryServiceImplTest extends AbstractTest {
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Before
	public void setup() throws Exception {
		deleteDatabase();
	}

	@Test
	public void testInsertProduct() {
		
		String productId = UUID.randomUUID().toString();
		
		Price price = new PriceImpl(productId, BigDecimal.valueOf(39.95), "EUR");
		
		Set<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(new AttributeImpl(productId, "title", "Handschuhe aus Biobaumwolle"));
		attributes.add(new AttributeImpl(productId, "description", "Schicke Unisex Strickhandschuhe aus 100% Biobaumwolle, fair und und ohne kritische Chemikalien hergestellt."));
		attributes.add(new AttributeImpl(productId, "image", "/images/products/product-GBOC695/Handschuhe_bio_fair.jpg"));
		
		Product newProduct = new ProductImpl(productId, attributes, price);
		repositoryService.saveProduct(newProduct);
		
		Product existingProduct = (Product) repositoryService.findCartItemById(productId);
		Assert.assertTrue(existingProduct != null && existingProduct.equals(newProduct));
	}
	
	@Test
	public void testInsertVariantProduct() {
		
		String productId = UUID.randomUUID().toString();
		String variantId = UUID.randomUUID().toString();
		
		Price price = new PriceImpl(variantId, BigDecimal.valueOf(15.95), "EUR");
		
		Set<Attribute> attributes = new HashSet<Attribute>();
		attributes.add(new AttributeImpl(productId, "title", "T-Shirt aus Bio-Baumwolle in div. Farben und Grössen"));
		attributes.add(new AttributeImpl(productId, "description", "Hochwertiges Biobaumwoll-T-Shirt für Männer aus extraweichem, \"mitteldickem\" Stoff gefertigt."));
		attributes.add(new AttributeImpl(productId, "image", "/images/products/product-GSTTM516/default.jpg"));				
		
		Set<String> variantAttributeTuple = new HashSet<String>();
		Collections.addAll(variantAttributeTuple, new String[] { "color", "size" });
		
		Set<Attribute> variantAttributes = new HashSet<Attribute>();
		variantAttributes.add(new AttributeImpl(variantId, "title", "T-Shirt aus Bio-Baumwolle in L, grün"));
		variantAttributes.add(new AttributeImpl(variantId, "orderId", "GSTTM516-221"));
		variantAttributes.add(new AttributeImpl(variantId, "color", "green"));
		variantAttributes.add(new AttributeImpl(variantId, "size", "L (x=52cm y=72cm)"));
		variantAttributes.add(new AttributeImpl(variantId, "image", "/images/products/product-GSTTM516/green.jpg"));
		
		Variant variant1 = new VariantImpl(variantId, productId, variantAttributes, price);
		
		Set<Variant> variants = Collections.singleton(variant1);
		
		VariantProduct newProduct = new VariantProductImpl(productId, attributes, variants, variantAttributeTuple);
		repositoryService.saveProduct(newProduct);
		
		VariantProduct existingProduct = (VariantProduct) repositoryService.findCartItemById(productId);
		Assert.assertTrue(existingProduct != null && existingProduct.equals(newProduct));
	}
	
	@Test
	public void testInsertInventory() {
		
		String productId = UUID.randomUUID().toString();
		
		InventoryPosition newInventoryPosition = new InventoryPositionImpl(productId, Integer.valueOf(12));
		repositoryService.saveInventory(newInventoryPosition);
		
		InventoryPosition foundInventoryPosition = repositoryService.findInventoryByProductId(productId);
		Assert.assertTrue(foundInventoryPosition != null && foundInventoryPosition.equals(newInventoryPosition));
		
		List<InventoryPosition> inventoryPositions = repositoryService.findAllInventories();
		Assert.assertTrue(inventoryPositions != null && inventoryPositions.size() == 1);
	}
	
}
