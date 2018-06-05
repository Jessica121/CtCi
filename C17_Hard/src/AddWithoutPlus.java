
public class AddWithoutPlus {
	
	/*
	 * Add two number without + arithmetic operators
	 * 
	 * using bit manipulation, if arithmetic is not allowed.
	 * Add two numbers with XOR, the carry using & << by 1 for the next function call.
	 * next call: carry will be the second num to add, XOR result will be the first number to add.
	 * carry is the carry for all the next digits, all shift left by 1.
	 * 
	 * */
	
	public static int add(int a, int b) {
		while(b != 0) {
			// save the result as it will be used later..
			int sum = a ^ b;
			b = (a & b) << 1;
			a = sum;
		}
		return a;
	}

	public static int addRecu(int a, int b) {
		// stop when the flag is 0.
		if(b == 0) return a;
		return addRecu((a ^ b), (a & b) << 1);
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			for(int j = 9; j >= 0; j--) {
				System.out.println(i + " + " + j + " == " + addRecu(i, j));
			}
		}
	}

}
