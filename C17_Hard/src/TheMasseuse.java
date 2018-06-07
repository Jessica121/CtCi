import java.util.Arrays;

public class TheMasseuse {
	/*
	 * Given an array, pick elements that no one adjacent to each other and get the max sum.
	 * 
	 * {30, 15, 60, 75, 45, 15, 15, 45} -> 180 {30, 60, 45, 45}
	 * 
	 * 1. recursion: add next index + 2 + this. 
	 * 				 dont add: index + 1
	 * return the max of either one.
	 * when index >= arr.length return 0;
	 * each one may be used be previous two parents. cache the result.
	 * 
	 * */
	
	public static int maxMinutes(int[] arr, int index, int[] memo) {
		// 30, 15, 60, 75
		// memo: 105 90 75 75
		if(index >= arr.length) return 0;
		if(memo[index] != 0) return memo[index];
		int choose = arr[index] + maxMinutes(arr, index + 2, memo);
		int notChoose = maxMinutes(arr, index + 1, memo);
		memo[index] = Math.max(choose, notChoose);
		return memo[index];
	}
	
	/*
	 * Use an dp array start from the end, same thing with recursion + memo
	 * use self + dp[i + 2] or use dp[i + 1], get the best one.
	 * return dp[0]
	 * */
	
	public static int maxMinDP(int[] arr) {
		int[] dp = new int[arr.length + 2];
		for(int i = arr.length - 1; i >= 0; i--) {
			dp[i] = Math.max(dp[i + 1], arr[i] + dp[i + 2]);
		}
		return dp[0];
	}
	
	/*
	 * for DP problems, if only use limited previous / later ones. reduce the space into just needed ones.
	 * prev1 = prev2 = 0
	 * prev2 = prev1
	 * prev1 = cur
	 * use prev1 or cur + prev2
	 * 
	 * */
	
	public static int maxDPLessSpace(int[] arr) {
		//		  30, 15, 60, 75
		// prev1  0  75  75  90  105
 		// prev2  0  0   75  75  90
		int prev1 = 0, prev2 = 0, cur = 0;
		for(int i = arr.length - 1; i >= 0; i--) {
			cur = Math.max(prev1, arr[i] + prev2);
			prev2 = prev1;
			prev1 = cur;
		}
		return cur;
	}

	public static void main(String[] args) {
		int[] arr = {30, 15, 60, 75};
		int[] memo = new int[arr.length];
		int res = maxMinutes(arr, 0, memo);
		System.out.println(Arrays.toString(memo) + ", res: " + res);
		System.out.println(maxDPLessSpace(arr));
	}

}
