package floor_assets;
import java.util.ArrayList;

public class Floor {
	public ArrayList<Zone> zones;
	
	public Floor(){
		this.zones = new ArrayList<Zone>();
	}
	
	public Floor(int num){
		this.zones = new ArrayList<Zone>();
		for (int i=0;i<num;i++){
			Zone a = new Zone();
			this.zones.add(a);
		}
	}
	
}
