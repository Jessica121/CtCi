
public class numberMax {
// find the max of two numb without using the if-else and comparison op 
	// Integer_MAX : 31 个 1， -1 32 个 1: 负数的第一位是1
	public static void main(String[] args) {
		System.out.println(getMax(20,3));
	}
	
	private static int getMax(int a, int b) {
		// 3 4 ; 3-4 = -1; 4-3= 1
		// get the sign of a - b, if negative, then first bit 1, positive 0. 
		// we want a * (1 - sign) + b * sign
		int sign = sign(a - b);
		return a * (1 - sign) + b * sign;
	}
	
	private static int sign(int a) {
		return 1 & (a >> 31);
	}

}
