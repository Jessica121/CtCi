import java.util.*;
public class rand7FromRand5 {

	public static void main(String[] args) {
		int[] arr = new int[7];
			for(int i = 0; i < 40000; i++) {
				int ind = rand7UsingTwoRand5();
				arr[ind]++;
			}
		System.out.println(Arrays.toString(arr));
	}

	private static Random ran = new Random();
	private static int rand5() {
		return ran.nextInt(5);
	}
	
	public static int rand7Too() {
		while(true) {
			int sum = rand5() * 5 + rand5();
			if(sum < 21) return sum % 7;
		}
	}
	
	private static int rand7() {
		int res = -1;
		while(res == -1) {
			int five = rand5() * 5 + rand5();
			if(five < 21) res = five % 7;
		}
		return res;
	}
	
	private static int rand7UsingTwoRand5() {
		// 0 2 4 6 8 rand5() * 2
		// gap: rand5() - 0(0) 1(1) 2(0) 3(1) 4, discard 4; % 2
		while(true) {
			int even = rand5() * 2;
			int gap = rand5();
			if(gap != 4) {
				int sum = even + gap % 2;
				// only return when sum < 7
				if(sum < 7) return sum; 
			}
		}
	}
}
