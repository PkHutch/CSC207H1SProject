package entities;

import java.util.ArrayList;
import entities.Stock;

public class Pallet {
	private ArrayList<Integer> content;

	public Pallet() {
		this.content = new ArrayList<Integer>();
	}

	public void addContent(Integer[] items) {
		for(int i = 0; i < items.length; i++) {
			this.content.add(items[i]);
		}
	}
}