package floor_assets;


public class Shelf{
	private Rack[] Rack;
	
	public Shelf(){
		this.Rack = new Rack[3];
	}
	
	public Shelf(int num){
		this.Rack = new Rack[num];
	}
}