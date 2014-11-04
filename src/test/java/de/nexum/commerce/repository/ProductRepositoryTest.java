package de.nexum.commerce.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.nexum.commerce.domain.product.Price;
import de.nexum.commerce.domain.product.Product;
import de.nexum.commerce.domain.product.impl.EuroPriceImpl;
import de.nexum.commerce.domain.product.impl.ProductImpl;
import de.nexum.test.AbstractMongoTest;

/**
 * @author <a href="mailto:thomas.weckert@nexum.de">Thomas Weckert</a>
 */
@ContextConfiguration(locations = {"classpath:/test-application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductRepositoryTest extends AbstractMongoTest {

	@Autowired private ProductRepository productRepository;
	
	@Test
    public void testInsert() {
				
		Price price = new EuroPriceImpl(BigDecimal.valueOf(39.95));
		
		Map<String, String> attributes = new HashMap<String, String>();
		attributes.put("title", "Handschuhe aus Biobaumwolle");
		attributes.put("description", "Schicke Unisex Strickhandschuhe aus 100% Biobaumwolle, fair und und ohne kritische Chemikalien hergestellt.");
		attributes.put("image", "/images/products/product-GBOC695/Handschuhe_bio_fair.jpg");
		
		Product product = new ProductImpl("product-GBOC695", attributes, price);		
		
		Product savedProduct = productRepository.save(product);
		Assert.assertTrue(savedProduct.equals(product));
		
		Product foundProduct = productRepository.findOne("product-GBOC695");
		Assert.assertTrue(foundProduct.equals(product));
	}
	
}
