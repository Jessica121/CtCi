import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhylosopher {
	public static DiningPhylosopher instance = new DiningPhylosopher();
	// break the cycle.
	public class Chopstick {
		private Lock lock;
		private int num;
		public Chopstick(int num) {
			this.lock = new ReentrantLock();
			this.num = num;
		}
		
		public void pickUp() {
			lock.lock();
		}
		
		public void putDown() {
			lock.unlock();
		}
		
		public int getNum() {
			return this.num;
		}
	}
	
	public class Phylosopher extends Thread {
		private Chopstick left, right;
		private int chew;
		private int ID;
		public Phylosopher(Chopstick lNum, Chopstick rNum, int chew, int ID) {
			this.left = lNum.getNum() < rNum.getNum() ? lNum : rNum;
			this.right = left == rNum ? lNum : rNum;
			this.chew = chew;
			this.ID = ID;
		}
		
		public void print() {
			System.out.println("phylosopher " + ID + " picked up left chop " + left.getNum() + ", right chop " + right.getNum());
		}
		
		public boolean pickUp() {
			// pick up the smaller one first.
			left.pickUp();
			right.pickUp();
			this.print();
			return true;
		}
		
		public void putDown() {
			right.putDown();
			left.putDown();
			System.out.println("		phylosopher " + ID + " put down his chops left " + left.getNum() + ", right chop " + right.getNum());
		}
		
		public void eat(int i) {
			System.out.println("	phylosopher " + ID + " says: yum " + i);
		}
		
		public void run() {
			for(int i = 0; i < this.chew; i++) {
				pickUp();
				eat(i);
				putDown();
			}
		}
	}
	
	public static void main(String args[]) {
		int people = 5;
		Phylosopher[] phy = new Phylosopher[people];
		Chopstick[] cho = new Chopstick[5];
		for(int i = 0; i < cho.length; i++) {
			cho[i] = instance.new Chopstick(i);
		}
		for(int i = 0; i < people; i++) {
			phy[i] = instance.new Phylosopher(cho[i % 5], cho[(i + 1) % 5], 5, i);
		}
		for(int i = 0; i < people; i++) {
			phy[i].start();
		}
		
	}
	
}
