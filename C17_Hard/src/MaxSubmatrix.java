import java.util.Arrays;

public class MaxSubmatrix {
	/*
	 * Given a matrix, find the max sub matrix has the max sum, does not matter if square or not
	 * 
	 * pre-compute the sum of sub matrix starting from (0, 0), could update everything from (0,0)
	 * 
	 * then compute all using substraction, starting from (1,1) to (len - 1, len - 1)
	 * 
	 * start row from 0 to len - 1, end row from start row to end row, within startrow - end row, start col - end col
	 * start col 0 - len - 1; end col - start col : len - 1
	 * start row 
	 * 		end row
	 * 			start col
	 * 				end col
	 * 
	 * */
	
	//O(n^2) + O(n^4)
	public static void maxSubMatrix(int[][] matrix) {
		int[][] dp = new int[matrix.length][matrix[0].length];
		computeSumFromOrigin(dp, matrix);
		for(int i = 0; i < dp.length; i++) System.out.println(Arrays.toString(dp[i]));
		int max = Integer.MIN_VALUE, startR = -1, endR = -1, startC = -1, endC = -1;
		for(int startRow = 0; startRow < matrix.length; startRow++) {
			for(int endRow = startRow; endRow < matrix.length; endRow++) {
				for(int startCol = 0; startCol < matrix[0].length; startCol++) {
					for(int endCol = startCol; endCol < matrix[0].length; endCol++) {
						int sum = getSum(matrix, startRow, endRow, startCol, endCol, dp);
						if(sum > max) {
							max = sum;
							startR = startRow;
							startC = startCol;
							endR = endRow;
							endC = endCol;
						}
					}
				}
			}
		}
		print(matrix, startR, endR, startC, endC);
	}
	
	/*
	 * Everything in left + self + up - dia, not merely substract the one up in the matrix.
	 * */
	private static void computeSumFromOrigin(int[][] dp, int[][] matrix) {
		for(int i = 0; i < dp.length; i++) {
			for(int j = 0; j < dp[0].length; j++) {
				int left = j - 1 >= 0 ? dp[i][j - 1] : 0, up = (i - 1 >= 0 ? dp[i - 1][j] : 0);
				int dia = (i - 1 >= 0 && j - 1 >= 0) ? dp[i - 1][j - 1] : 0;
				dp[i][j] = left + matrix[i][j] + up - dia;
			}
		}
	}
	
	/*
	 * dp[endR][endC] - dp[endR][startC - 1] - dp[startR - 1][endC] + matrix[startR - 1][startC - 1]
	 * */
	private static int getSum(int[][] matrix, int sR, int eR, int sC, int eC, int[][] dp) {
		// boundary check for boundary one.
		return dp[eR][eC] - (sC - 1 >= 0 ? dp[eR][sC - 1] : 0) - (sR - 1 >= 0 ? dp[sR - 1][eC] : 0) + (sR - 1 >= 0 && sC - 1 >= 0 ? dp[sR - 1][sC - 1] : 0);
	}
	
	private static void print(int[][] matrix, int sR, int eR, int sC, int eC) {
		System.out.println("Result is: startRow: " + sR + ", endRow: " + eR + ", startCol: " + sC + ", endCol " + eC);
		for(int i = sR; i <= eR; i++) {
			for(int j = sC; j <= eC; j++) System.out.print(matrix[i][j] + ", ");
			System.out.println();

		}
	}
	
	/*
	 * 2D -> 1D: fix the start row and end row, calculate the max sub matrix: add cols up in start row, as end row increases,
	 * modify on the original matrix, 
	 * as the start row will not be used when proceed.
	 * Get the max matrix.
	 * */
	// O(R^2 * C)
	public static void maxSubMatrixOptimal(int[][] matrix) {
		int max = Integer.MIN_VALUE, startR = -1, endR = -1, startC = -1, endC = -1;
		for(int startRow = 0; startRow < matrix.length; startRow++) {
			// since we have to print the orginal matrix, so cannot do it directly on the matrix.
			int[] arr = new int[matrix[0].length];
			for(int endRow = startRow; endRow < matrix.length; endRow++) {
				// O(C)
				for(int col = 0; col < matrix[0].length; col++) {
					arr[col] += matrix[endRow][col];
				}
				//O(C)
				int[] partailMax = maxSumArray(arr);
				if(partailMax[0] > max) {
					max = partailMax[0];
					startR = startRow;
					startC = partailMax[1];
					endR = endRow;
					endC = partailMax[2];
				}
			}
		}
		print(matrix, startR, endR, startC, endC);
	}
	/*
	 * If self + prev >= self, prev = self + prev, update max, start, end
	 * if prev + self >= self use it. else, rather just use itself 
	 * else prev = self, curStart = i
	 * */
	public static int[] maxSumArray(int[] arr) {
		if(arr.length == 0) return new int[] {0, 0, 0};
		int sum = Integer.MIN_VALUE, prev = 0, start = -1, end = -1, curStart = 0;
		for(int i = 0; i < arr.length ; i++) {
			if(prev + arr[i] >= arr[i]) prev = prev + arr[i];
			else {
				prev = arr[i];
				curStart = i;
			}
			if(prev > sum) {
				sum = prev;
				end = i;
				start = curStart;
			}
		}
		System.out.println(Arrays.toString(arr));
		System.out.println(sum + ", " + start + ", " + end);
		return new int[]{sum, start, end};
	}

	public static void main(String[] args) {
		int[][] m =  {
			            {1, 2, -1, -4, -20},
			            {-8, -3, 4, 2, 1},
			            {3, 8, 10, 1, 3},
			            {-4, -1, 1, 7, -6}
			          };
		maxSubMatrixOptimal(m);
	}

}
