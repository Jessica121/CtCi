
public class Display {
	private int curPage = 0;
	// reader and book are objects of parent class. passed by parent directly.
	// not  constructor / getter / setter
//	private Reader activeReader;
//	private Book activeBook;
	public Display() {
//		this.activeBook = b;
//		this.activeReader = r;
	}
	
	public void disPlayBook(Book book) {
		
	}
	
	public void disPlayReader(Reader r) {
		
	}
	
	public void flipFwd() {
		curPage++;
		refreshPage();
	} 
	
	public void flipBackwd() {
		curPage--;
		refreshPage();
	}
	
	private void refreshPage() {
		// display the content from the db 
	}
}
