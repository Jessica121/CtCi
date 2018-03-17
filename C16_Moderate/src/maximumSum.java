
public class maximumSum {
	// 2, -8, 3, -2, 4, -10
	public static void main(String[] args) {
		int[] arr = {2, 3, -8, -1, 2, 4, -2, 3};
		System.out.println(maxSum(arr));
	}
	
	private static int maxSum(int[] arr) {
//		if(arr == null || arr.length == 0) return 0;
		int maxSum = 0, sum = 0;
		for(int a : arr) {
			sum += a;
			maxSum = Math.max(sum, maxSum);
			// If partial sum < 0, reset sum to 0;
			sum = sum < 0 ? 0 : sum;
		}
		return maxSum;
	}

}
