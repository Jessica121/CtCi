
public class factorialZeros {

	public static void main(String[] args) {
//		for(int i = 1; i < 200000; i++) {
			if(trailingZeroes(1808548329) != countFactZeros(1808548329)) {
				System.out.println("somethings wrong at: " + 1808548329);
				System.out.println(trailingZeroes(1808548329) + " < > " + countFactZeros(1808548329));
			}
//		}
		System.out.println("Done");
	}
	

	public static String factorial(int num) {
		int res = 1;
		StringBuilder sb = new StringBuilder();
		while(num > 0) {
			sb.append(num).append("*");
			res *= num--;
		}
		return sb.toString() + " res-> " + String.valueOf(res);
	}
	
	public static int trailingZeroes(int n) {
        int five = 0;
		while(n > 0) {
                five += countFive(n);
                n --;
		}
		return five;
    }
    
    private static int countFive(int n) {
        int res = 0;
        while(n % 5 == 0) {
            res++;
            n /= 5;
        }
        return res;
    }
	
	/*This is not working as some num contains more than one 5, like 25 = 5 ^ 2*/
	private static int factorialZeroWrong(int n) {
		int boundToHave = n / 10 * 2;
		return n % 10 >= 5 ? boundToHave + 1 : boundToHave;
	}

	
	//Sample ans
	public static int countFactZeros(int num) {
		int count = 0;
		if (num < 0) {
			System.out.println("Factorial is not defined for negative numbers");
			return 0;
		}
		for (int i = 5; num / i > 0; i *= 5) {
			count += num / i;
		}
		return count;
	}


}
