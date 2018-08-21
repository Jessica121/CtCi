package CallCenter;

public class Director extends Employee{
	public Director(CallCenter callCenter) {
		super(callCenter);
		this.rank = Rank.DIRECTOR;
	}
	
	public int getRank() {
		return this.rank.getRank();
	}
}
