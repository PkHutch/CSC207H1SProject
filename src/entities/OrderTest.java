package entities;

import junit.framework.TestCase;

public class OrderTest extends TestCase {
    private String colour;
    private String model;
    private Order order;


	protected void setUp() throws Exception {
		this.colour = "apple";
		this.model = "SES";
		this.order = new Order(this.colour, this.model);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testGetColour() {
        assertEquals("apple", this.order.getColour());
    }

    public void testGetModel() {
    	assertEquals("SES", this.order.getModel());
    }

}
