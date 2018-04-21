
public class SyncTest extends Thread {
	private String name;
	private SyncMeth obj;
	
	public SyncTest(String name, SyncMeth obj) {
		this.name = name;
		this.obj = obj;
	}
	
	public void run() {
		try {
			obj.print4(name);
//			if(name.equals("one")) obj.print2(name);
//			if(name.equals("two")) obj.print3(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SyncMeth obj1 = new SyncMeth(), obj2 = new SyncMeth();
		// when called on the same obj, they cannot called at the same time. 
		// Different obj yes.
		SyncTest s1 = new SyncTest("one", obj1), s2 = new SyncTest("two", obj1);
		s1.start();
		s2.start();
	}
}
