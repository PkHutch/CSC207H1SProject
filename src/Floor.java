
public class Floor {
	private Aisle[] aisles;
	
	public Floor(){
		this.aisles = new Aisle[10000];
	}
	
	public Floor(int num){
		this.aisles = new Aisle[num];
	}
	
}

class Aisle {
	private Shelf[] Shelf;
	
	public Aisle(){
		this.Shelf = new Shelf[2];
	}
	
	public Aisle(int num){
		this.Shelf = new Shelf[num];
	}
}

class Shelf{
	private Rack[] Rack;
	
	public Shelf(){
		this.Rack = new Rack[3];
	}
	
	public Shelf(int num){
		this.Rack = new Rack[num];
	}
}

class Rack {
	private Level[] levels;
	
	public Rack(){
		this.levels = new Level[3];
	}
	
	public Rack(int num){
		this.levels = new Level[num];
	}
}

class Level {
	private int[] items;

	public Level(){
		this.items = new int[30];
	}
	
	public Level(int num){
		this.items = new int[num];
	}
}