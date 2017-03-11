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
	public Floor(int[]nums){
		if (nums.length == 5){
			int a = nums[0];
			int b = nums[1];
			int c = nums[2];
			int d = nums[3];
			int e = nums[4];
			
			this.zones = new ArrayList<Zone>();
			for(int i=0;i<a;i++){
				Zone z = new Zone(b,c,d,e);
				this.zones.add(z);
				}
			}else{
				System.out.println("Wrong Value Error");
			}
		}
		
	}
	
