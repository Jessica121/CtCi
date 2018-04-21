
public class SyncMeth {
	static int cnt = 0;
	
	// NON-STATIC SYNC
	public synchronized void print(String s) throws InterruptedException {
		while(cnt < 5) {
			System.out.println(s + " is running in print 1." + cnt);
			Thread.sleep(300);
			cnt++;
		}
		cnt = 0;
	}
	
	// static sync
	/*
	 * Static methods syc on the same class have to be called one by one.
	 * even if different methods
	 * */
	public synchronized static void print2(String s) throws InterruptedException {
		while(cnt < 5) {
			System.out.println(s + " is running in print 2." + cnt);
			Thread.sleep(300);
			cnt++;
		}
		cnt = 0;
	}
	
	public synchronized static void print3(String s) throws InterruptedException {
		while(cnt < 5) {
			System.out.println(s + " is running in print 3." + cnt);
			Thread.sleep(300);
			cnt++;
		}
		cnt = 0;
	}
	
	/*
	 * Sync blocks.
	 * */
	public void print4(String s) throws InterruptedException {
		synchronized (this) {
			while(cnt < 5) {
				System.out.println(s + " is running in print 4." + cnt);
				Thread.sleep(300);
				cnt++;
			}
			cnt = 0;
		}
	}

}
