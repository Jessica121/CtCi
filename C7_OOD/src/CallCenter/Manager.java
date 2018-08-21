package CallCenter;

public class Manager extends Employee {
	public Manager(CallCenter callCenter) {
		super(callCenter);
		this.rank = Rank.MANAGER;
	}
	
	public int getRank() {
		return this.rank.getRank();
	}
}
