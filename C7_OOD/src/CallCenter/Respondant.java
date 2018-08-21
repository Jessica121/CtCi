package CallCenter;

public class Respondant extends Employee {
	public Respondant(CallCenter callCenter) {
		super(callCenter);
		this.rank = Rank.RESPONDANT;
	}
	
	public int getRank() {
		return this.rank.getRank();
	}
}
