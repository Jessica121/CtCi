
public class numberMax {
// find the max of two numb without using the if-else and comparison op 
	// Integer_MAX : 31 个 1， -1 32 个 1: 负数的第一位是1
	public static void main(String[] args) {
		System.out.println(getMax(2147483647,-15));
	}
	
	private static int getMax(int a, int b) {
		// 3 4 ; 3-4 = -1; 4-3= 1
		// get the sign of a - b, if negative, then first bit 1, positive 0. 
		// we want a * (1 - sign) + b * sign
		int sign = sign(a - b);
		int diffSign = sign(a) ^ sign(b); // 1 if they have diff sign, 0 if not.
//		System.out.println(diffSign);
		return a * (sign(a) ^ diffSign) + b * (sign(b) ^ diffSign);
	}
	
	private static int sign(int a) {
		return 1 & (a >> 31);
	}

}
