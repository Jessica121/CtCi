
public class numberSwapper {
// Swap a number in place, without ANY temp vars.
// a = 5, b = 9 -> a = 9, b = 5
	
	public static void main(String[] args) {
		int a = 5, b = 9;
		int[] res = swap(a, b);
		System.out.println(res[0] + " " + res[1]);
	}
	
	private static int[] swap(int a, int b) {
		a = a - b;
		b = a + b;
		a = b - a;
		return new int[] {a, b};
	}
}
