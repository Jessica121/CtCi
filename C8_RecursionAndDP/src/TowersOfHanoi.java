import java.util.Stack;

public class TowersOfHanoi {
	/* sta0, sta1, sta2
	 * 
	 * f(n, 2) = f(n - 1, 1) + put n to 2 + f(n - 1, 2)
	 * 
	 * f(n, dest) = f(n - 1, buffer) + put n to 2 + f(n - 1, dest)
	 * 
	 * 思想局限在于想不到可以在输入制定 src，buffer，destination
	 * 而且src buf dest 在动态转换。
	 * 【f(n, src, buf, dest) = f(n - 1, src, dest, buf) + put(n, dest) + f(n - 1, buf, src, dest)】
	 * 
	 */
	
	// this wont work!
//	public static void moveHanoi(int n, Stack<Integer> sta2) throws Exception {
//		if(n == 0) return;
//		moveHanoi(n - 1, sta1);
//		if(!sta2.isEmpty() && sta2.peek() < n) throw new Exception("error pushing " + n + " into stack " + sta2);
//		sta2.push(n);
//		moveHanoi(n - 1, sta2);
//	}
	
	// O(2 ^ n)
	public static void moveHanoi(int n, Stack<Integer> src, Stack<Integer> buf, Stack<Integer> dest) throws Exception {
		if(n == 0) return;
		System.out.println(n + "," + src + "," + buf + ", " + dest);
		moveHanoi(n - 1, src, dest, buf);
		if(!dest.isEmpty() && dest.peek() < src.peek()) throw new Exception("error pushing " + src.peek() + " into stack " + dest);
		dest.push(src.pop());
		moveHanoi(n - 1, buf, src, dest);
	}
	
	public static void main(String[] args) throws Exception {
		Stack<Integer> sta0 = new Stack<>(), sta1 = new Stack<>(), sta2 = new Stack<>();
		sta0.push(4);
		sta0.push(3);
		sta0.push(2);
		sta0.push(1);
		sta0.push(0);
		moveHanoi(5, sta0, sta1, sta2);
		System.out.println(sta0 + "," + sta1 + "," + sta2);
	}
	
}
