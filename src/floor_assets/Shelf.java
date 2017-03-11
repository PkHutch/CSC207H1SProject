package floor_assets;
import java.util.ArrayList;
/*
 * Shelves of Aisles
 */
public class Shelf{
	private ArrayList<Rack> rack;
	/*
	 * Initiaze A flexable shelf
	 */
	public Shelf(){
		this.rack = new ArrayList<Rack>();
	}
	/*
	 * Part of the auto complete, can be called if needed
	 */
	public Shelf(int a,int b){
		for(int i=0;i<a;i++){
			Rack x = new Rack(b);
			this.rack.add(x);
		}
	}
}