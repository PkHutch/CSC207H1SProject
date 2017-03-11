package floor_assets;

public class Aisle {
	private Shelf[] Shelf;
	
	public Aisle(){
		this.Shelf = new Shelf[2];
	}
	
	public Aisle(int num){
		this.Shelf = new Shelf[num];
	}
}