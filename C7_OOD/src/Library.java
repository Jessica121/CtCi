
import java.util.*;
public class Library {
	private Map<String, Book> map;
	public Library() {
		map = new HashMap<>();
	}
	
	public void addBook(Book b) {
		map.put(b.getSerail(), b);
	}
	
	public void removeBook(Book b) {
		map.remove(b.getSerail());
	}
	
}
