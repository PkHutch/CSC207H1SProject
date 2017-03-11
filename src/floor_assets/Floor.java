package floor_assets;

public class Floor {
	private Aisle[] aisles;
	
	public Floor(){
		this.aisles = new Aisle[10000];
	}
	
	public Floor(int num){
		this.aisles = new Aisle[num];
	}
	
}
