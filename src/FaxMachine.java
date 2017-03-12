import java.util.LinkedList;

public class FaxMachine {
	private LinkedList<Order> orders;

	public FaxMachine() {
		this.orders = new LinkedList<Order>();
	}

	public FaxMachine(Order order) {
		this.orders = new LinkedList<Order>();
		this.orders.add(order);
	}

	public void addOrder(Order o) {
		this.orders.add(o);
	}

	public Order removeOrder(Order o) {
		return this.orders.removeFirst();
	}

	public LinkedList<Order> getOrders() {
		return this.orders;
	}
}
