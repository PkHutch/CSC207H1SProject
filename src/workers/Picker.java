package workers;
import vehicles.*;

public class Picker extends Worker {

	private Forklift forklift;

	public Picker(String name) {
		super(name);
		this.forklift = new Forklift();
	}

	public void doTask() {
		;
	}
}
