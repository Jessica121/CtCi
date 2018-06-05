
public class countOf2s {
	/*
	 * Count the number of 2s from 0 to n.
	 * 
	 * brute force:
	 * iterate 0 to n, count 2s in each number, add up.
	 * 
	 * 
	 * */
	
	public static int numOf2s(int n) {
		int res = 0;
		for(int i = 0; i <= n; i++) {
			res += numOfTwo(i);
		}
		return res;
	}
	
	private static int numOfTwo(int n) {
		// 2222
		int res = 0;
		while(n != 0) {
			if(n % 10 == 2) res++;
			n /= 10;
		}
		return res;
	}
	
	/*
	 * count digit by digit
	 * if digit < 2, Math.pow(10, digit) * everything prev (0 is the first digit)
	 * if digit == 2, Math.pow(10, digit) * everything prev + everything next + 1
	 * else digit > 2, Math.pow(10, digit + 1) * everything prev
	 * 
	 * */
	
	public static int countOf2sByDigit(int n) {
		// 9999
		int i = 0, prev = n, later = 0, res = 0;
		while(n > 0) {
			int digit = n % 10;
			prev /= 10;
			if(digit < 2) res += prev * Math.pow(10, i);
			else if(digit == 2) res += (prev * Math.pow(10, i) + later + 1);
			else res += (prev + 1) * Math.pow(10, i);
			// later was not later = later * 10 + digit. will be in reverse order. 
			// should be later = digit * Math.pow(10, i) + later, the same as calculating the value of base 10 number.
			later += digit * Math.pow(10, i);
			i++;
			n /= 10;
		}
		return res;
	}

	public static void main(String[] args) {
		for(int i = 0; i < 1000; i++) {
			int exp = numOf2s(i), act = countOf2sByDigit(i);
			if(exp != act) System.out.println("Error at " + i + " expected " + exp + " actual " + act); 
		}
//		System.out.println(countOf2sByDigit(201));
		
	}

}
