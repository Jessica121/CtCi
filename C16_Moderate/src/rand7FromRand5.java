import java.util.*;
public class rand7FromRand5 {

	public static void main(String[] args) {
		int[] arr = new int[7];
//		for(int j = 0; j < 5; j++) {
			for(int i = 0; i < 2000; i++) {
				int ind = rand7();
				arr[ind]++;
			}
//		}
		System.out.println(Arrays.toString(arr));
	}

	private static Random ran = new Random();
	private static int rand5() {
		return ran.nextInt(5);
	}
	private static int rand7() {
		int res = -1;
		while(res == -1) {
			int five = rand5() * 5 + rand5();
			if(five < 21) res = five % 7;
		}
		return res;
	}
}
