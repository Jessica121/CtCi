
public class Book {
	private String name;
	private String serialNum;
	
	public Book(String n, String serial) {
		this.name = n;
		this.serialNum = serial;
	}
	
	public String getSerail() {
		return this.serialNum;
	}
	
	public String getName() {
		return this.name;
	}
	
}
