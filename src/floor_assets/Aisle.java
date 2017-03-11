package floor_assets;
import java.util.ArrayList;
/*
 * The aisles from a zone
 */
public class Aisle {
	private ArrayList<Shelf> shelf;
	/*
	 * Initialze a flexable shelf for the Aisle
	 */
	public Aisle(){
		this.shelf = new ArrayList<Shelf>();
	}
	/*
	 * Part of the auto-complete function from Floor
	 * Can be called for specific specs
	 */
	public Aisle(int a,int b,int c){
		for(int i=0;i<a;i++){
			Shelf x = new Shelf(b,c);
			this.shelf.add(x);
		}
	}
}