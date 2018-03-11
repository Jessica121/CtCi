
public class operationWithAdd {
/*
 * Operation of substracting , multiplying and dividing using only ADD
 * */
	public static void main(String[] args) {
		System.out.println(divide(-10, -5));

	}
	/*Substrating*/
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
//			System.out.println(a + " " + sign);
		}
		return res;
	}
	
	/*Multiply*/
	// The most trivial way is to add a b times...
	private static int mul(int a, int b) {
		// a , b < 0
		if(abs(b) > abs(a)) return mul(b, a); // faster with smaller times
		int res = 0, cnt = abs(b); // mul b times
		while(cnt-- > 0) {
			res += a;
		}
		if(b < 0) res = neg(res);
		return res;
	}
	
	private static int abs(int a) {
		if(a >= 0) return a;
		else return neg(a);
	}
	
	/*Divide
	 * assume the res will only be integers*/
	private static int divide(int a, int b) throws java.lang.ArithmeticException {
		if(b == 0) throw new java.lang.ArithmeticException("Divide by zero!");
		int res = 0;
		for(int i = 1; mul(i, abs(b)) <= abs(a); i++) {
			if(mul(i, abs(b)) == abs(a)) res = i;
		}
		if(mul(a, b) < 0) res = neg(res);
		if(res == 0) throw new java.lang.ArithmeticException("Not a integer res!");
		else return res;
	}

}
