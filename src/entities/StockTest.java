package entities;

import junit.framework.TestCase;

public class StockTest extends TestCase {
	private Stock stock;
	private String sku;

	protected void setUp() throws Exception {
		this.sku = "1";
		this.stock = new Stock(this.sku);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    public void testGetSKU() {
        assertEquals("1", this.stock.getSKU());
    }

}
