import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class RedPencilTest {

	Product product;
	Product redPencilProduct;
	@Before
	public void setUp(){
		product = new Product();
		redPencilProduct = new Product();
		redPencilProduct.setLastPrice(15);
		redPencilProduct.setCurrentPrice(10);
	}
	
	@Test
	public void redPencilChecksThatProductIsReducedByAtLeastFivePercent() {
		assertEquals(false, RedPencil.checkProductEligibility(product));
		assertEquals(true, RedPencil.checkProductEligibility(redPencilProduct));

	}
	
}
