
public class RunnableExample implements Runnable {
	int count;
	String name;
	public RunnableExample(String n) {
		count = 0;
		name = n;
	}
	
	public void run() {
		System.out.println("Starting the runnable." + this.name);
		try {
			System.out.println("In try block at " + this.name);
			while(count < 5) {
				count++;
				Thread.sleep(400);
				System.out.println(this.name + " increased to " + count);
			}
		} catch (InterruptedException e) {
			System.out.println("Runnable Thread interrupted.");
		} finally {
			System.out.println("Exiting the runnable." + this.name);
		}
	}
	
	public static void main(String[] args) {
		RunnableExample runObj = new RunnableExample("one");
		Thread t = new Thread(runObj), t1 = new Thread(runObj);
		t.start();
		t1.start();
	}
}
