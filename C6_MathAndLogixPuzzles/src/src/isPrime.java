package src;

public class isPrime {
	// check if a given num is prime
	public static void main(String[] args) {
		// prime 2, 3, 5, 7, 11
		// 121 = 11 * 11
		int num = 113 * 113;
		System.out.println(isPrime(num));

	}
	
	private static boolean isPrime(int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}

}
