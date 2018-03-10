
public class operationWithAdd {
/*
 * Operation of substracting , multiplying and dividing using only ADD
 * */
	public static void main(String[] args) {
		System.out.println(substract(29,29));

	}
	
	private static int substract(int a, int b) {
		return a + negFaster(b);
	}
	
	private static int neg(int a) {
		int sign = a < 0 ? 1 : -1;
		int res = 0;
		while(a != 0) {
//			System.out.println(a);
			a += sign;
			res += sign;
		}
//		System.out.println(res);
		return res;
	}
	
	private static int negFaster(int a) {
		int sign = a < 0 ? 1 : -1;
		int oriSign = sign;
		int res = 0;
		while(a != 0) {
			boolean signChanged = ((a + sign) > 0) && a < 0 || ((a + sign) < 0) && a > 0;
			if(signChanged) sign = oriSign;	//Math.abs(a) < Math.abs(sign)
			a += sign;
			res += sign;
			sign += sign;
			System.out.println(a + " " + sign);
		}
		return res;
	}

}
