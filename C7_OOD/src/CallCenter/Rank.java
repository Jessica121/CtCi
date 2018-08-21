package CallCenter;

public enum Rank {
	RESPONDANT(0), MANAGER(1), DIRECTOR(2);
	private int rank;
	private Rank(int r) {
		this.rank = r;
	}
	
	public int getRank() {
		return this.rank;
	}
}
