package warehouse;

import java.util.LinkedList;

public class FaxMachine {
	private LinkedList<Order> orders;
	private Server server;

	public FaxMachine() {
		this.orders = new LinkedList<Order>();
	}

	public FaxMachine(Server s) {
		this.orders = new LinkedList<Order>();
		this.server = s;
	}

	public FaxMachine(Order order) {
		this.orders = new LinkedList<Order>();
		this.orders.add(order);
	}

	public void addOrder(Order o) {
		this.orders.add(o);
	}

	public Order removeOrder() {
		return this.orders.removeFirst();
	}

	public LinkedList<Order> getOrders() {
		return this.orders;
	}

	public void doTask(String argument) {
		this.server.issueTask(this);
	}
}
