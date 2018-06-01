
public class FractionalToBinary {
	
	/* give a float number, 0 < num < 1, convert it to binary rep
	 *  continuesly left shift by 1, check the integer part.
	 *  or compare to 0.5, 0.25, 0.125, ...
	 *  
	 *  e.g. 0.75 
	 *       0.75 << 1 = 1.5 -> 1
	 *       0.5  << 1 = 1.0 -> 1
	 *       binary 0.11
	 */

	public static String binaryToString(double num) {
		if(num <= 0 || num >= 1) return "ERROR";
		StringBuilder sb = new StringBuilder("0.");
		while(num > 0) {
			num *= 2;
			if(num >= 1) {
				sb.append(1);
				num -= 1;
			} else sb.append(0);
			if(sb.length() == 32) break; // if cannot represent within 32 bits, stop when length == 32
		}
		return sb.toString();
	}
	
	public static String binaryToString2(double num) {
		if(num <= 0 || num >= 1) return "ERROR";
		StringBuilder sb = new StringBuilder("0.");
		double cmp = 0.5;
		while(num > 0) {
			if(num >= cmp) {
				num -= cmp;
				sb.append(1);
			} else sb.append(0);
			cmp /= 2;
			if(sb.length() == 32) break;
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(binaryToString(0.72));
		System.out.println(binaryToString2(0.72));
	}

}
