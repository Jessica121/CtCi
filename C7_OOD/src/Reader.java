
public class Reader {

	private String id;
	private boolean isPremium;
	
	public Reader(String id, boolean isP) {
		this.id = id;
		this.isPremium = isP;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void resetId(String id) {
		this.id = id;
	}
	
	public boolean isP() {
		return isPremium;
	}
	
	public void setPre(boolean status) {
		this.isPremium = status;
	}
	
}
