package floor_assets;
import java.util.ArrayList;
/*
 * The floor of a warehouse
 */
public class Floor {
	public ArrayList<Zone> zones;
	
	/*
	 * Initialize according to job specifications;
	 */
	public Floor(){
		this.zones = new ArrayList<Zone>();
	}
	/*
	 * Calls for a Floor with a certain number of Zones
	 */
	public Floor(int num){
		this.zones = new ArrayList<Zone>();
		for (int i=0;i<num;i++){
			Zone a = new Zone();
			this.zones.add(a);
		}
	}
	/*
	 * Auto Completes the Entire area with one array containing the values of each 
	 * sub zones
	 */
	public Floor(int a, int b,int c,int d,int e){
			
			this.zones = new ArrayList<Zone>();
			for(int i=0;i<a;i++){
				Zone z = new Zone(b,c,d,e);
				this.zones.add(z);
			}
		}
		
	}
	
