package de.nexum.commerce.manager;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.nexum.commerce.domain.product.Attribute;
import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.impl.AttributeImpl;
import de.nexum.commerce.domain.product.impl.PriceImpl;
import de.nexum.commerce.domain.product.impl.ProductImpl;
import de.nexum.test.AbstractTest;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@ContextConfiguration(locations = {"classpath:/test-application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductManagerTest extends AbstractTest {
	
	@Autowired
	private ProductManager productManager;
	
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
		productManager.insertProduct(newProduct);
		
		Product existingProduct = productManager.findByID(productId);
		Assert.assertTrue(existingProduct != null && existingProduct.equals(newProduct));
	}
	
}
