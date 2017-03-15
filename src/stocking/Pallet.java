package stocking;

import java.util.ArrayList;

public class Pallet {
	private ArrayList<Fascia> content;

	public Pallet() {
		this.content = new ArrayList<Fascia>();
	}

	public void addContent(Fascia f) {
		this.content.add(f);
	}

	@SuppressWarnings("unused")
	public Fascia removeContent(Fascia f) {
		for (int i = 0; i < this.content.size(); i++) {
			if (this.content.get(i) == f) {
				this.content.remove(f);
				return f;
			} else {
				return null;
			}
		}
		return f;

	}
}
