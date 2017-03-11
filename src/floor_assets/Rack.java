package floor_assets;
import java.util.ArrayList;
/*
 * Racks of a Shelf
 */
public class Rack {
	private ArrayList<Level> level;
	/*
	 * Initiaze a flexable Rack for the Shelf.
	 */
	public Rack(){
		this.level = new ArrayList<Level>();
	}
	/*
	 * Part of the auto-complete, can be called alone if needed.
	 */
	public Rack(int num){
		this.level = new ArrayList<Level>();
		for(int i=0;i<num;i++){
			Level x = new Level();
			this.level.add(x);
		}
	}
}
