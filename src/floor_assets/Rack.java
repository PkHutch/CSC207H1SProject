package floor_assets;

public class Rack {
	private Level[] levels;
	
	public Rack(){
		this.levels = new Level[3];
	}
	
	public Rack(int num){
		this.levels = new Level[num];
	}
}
