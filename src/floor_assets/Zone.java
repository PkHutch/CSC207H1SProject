package floor_assets;

public class Zone {
	private Aisle[] Shelf;
	
	public Zone(){
		this.Shelf = new Aisle[2];
	}
	
	public Zone(int num){
		this.Shelf = new Aisle[num];
	}
}

