package entities;

import java.util.ArrayList;

public class PickingRequest {
	
    private final Integer[] skus;
	private int status;
	 /*
	  * For status, the following are the possible values:
	  * 0 : unassigned
	  * 1 : assigned
	  * 2 : awaiting sequencing
	  * 3 : sequenceable
	  */
	
	public PickingRequest(Integer[] skus) {
		this.skus = skus;
		this.status = 0;
	}

	public Integer[] getSkus() {
		return this.skus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
