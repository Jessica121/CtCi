public class OnlineBookReader {
// Constructor, Encap(getter n setter), Polymorph, Inheritence, Overriding, interface and absraction
	// bunch of readers at a time, each with one active book.
	private UserManager manag;
	private Reader activeReader;
	private Book activeBook;
	private Library lib;
	private Display dis;
	
	public OnlineBookReader() {
		// you can set the reader and book later. (?)
//		this.activeReader = new Reader();
//		this.activeBook = new Book();
	
//		this.dis = new Display(activeReader, activeBook);
		this.dis = new Display();
		this.manag = new UserManager();
		this.lib = new Library();
	}
	
	public Reader getActReader() {
		return this.activeReader;
	}
	public void setActReader(Reader r) {
		activeReader = r;
		dis.disPlayReader(r);
	}
	public Book getActBook() {
		return activeBook;
	}
	public void setActBook(Book b) {
		this.activeBook = b;
		dis.disPlayBook(b);
	}
	
	public void flipPageForward() {
		dis.flipFwd();
	}
	
	public void flipPageBackward() {
		dis.flipBackwd();
	}
	
	// Well all private fields were suppose to have getter and setters.
	public Library getLib() {
		return this.lib;
	}
	
	public UserManager getM() {
		return manag;
	}
	
	public void setPreium(Reader r) {
		manag.setPreium(r);
	}
	
	public void deactivatePreium(Reader r) {
		manag.deactivatePreium(r);
	}
}
