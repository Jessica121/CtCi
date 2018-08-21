package CallCenter;

/*
 * object: rank 
 * employee,
 * 
 * action: increase the rank.
 * hang up
 * assgin Handler
 * 
 * */
public class Call {
	private int rank;
	private Employee handler;
	public Call(int rank) {
		this.rank = rank;
		this.handler = null;
	}
	public void assignHandler(Employee handler) {
		this.handler = handler;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	// not sure about this since the rank need to be 0 - 2
	public void increaseRank() {
		if(this.rank >= 2) System.out.println("CANNOT INCREASE - HIGHEST RANK ALREADY.");
		else this.rank++;
	}
	
	public void hangUp() {
		this.handler = null;
	}

}
