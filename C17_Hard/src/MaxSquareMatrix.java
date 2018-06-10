
public class MaxSquareMatrix {
	/*
	 * Given a matrix, find the max square that all four borders are black (1)
	 * 
	 * check from largest(matrix.length) to smallest(1)
	 * check(row, col, len) where row = 0, col = 0 till 0 .. width - len
	 * len = width .. 1
	 * o(n^4) bf, checking function takes up most of the time.
	 * from a start point and its length, want to use cache to check its 4 borders.
	 * use off set to the nearest white cell + 1. if 0 1 1 1, then the second and forth will have offset 2 3 4, 
	 * if 2 + 2(len) == 4 then the row is okay. same thing for the rest 3
	 * then O(n^3) time
	 * O(4*n^2) space
	 * print the square by using top and offset
	 * on the border black cell be 0 or 1 both okay. offset will be same
	 * 
	 * 0 1 0
	 * 1 1 0
	 * 1 1 0
	 * 
	 * row 
	 * 0 1 0
	 * 1 2 0
	 * 1 2 0
	 * 
	 * col
	 * 0 1 0
	 * 1 2 0
	 * 2 3 0
	 * 
	 * 
	 * */

	//O(N^3)
	public static void maxSquare(int[][] square) {
		int row = 0, col = 0;
		boolean found = false;
		Cell[][] dp = new Cell[square.length][square.length];
		buildDP(square, dp);
		for(int len = square.length; len > 0; len--) {
			for(row = 0; row <= square.length - len; row++) {
				for(col = 0; col <= square.length - len; col++) {
					// only check when the current cell is 1.
					if(!found && square[row][col] == 1 && check(square, row, col, len, dp)) {
						print(square, row, col, len);
						found = true;
					}
				}
			}
		}
		if(!found) System.out.print("Not found");
	}
	
	private static boolean check(int[][] squ, int row, int col, int len, Cell[][] dp) {
		if(dp[row][col + len - 1].rowOffset - dp[row][col].rowOffset != len - 1 || 
		   dp[row + len - 1][col].colOffset - dp[row][col].colOffset != len - 1 ||
		   dp[row + len - 1][col + len - 1].colOffset - dp[row][col + len - 1].colOffset != len - 1 ||
		   dp[row + len - 1][col + len - 1].rowOffset - dp[row + len - 1][col].rowOffset != len - 1) return false;
		return true;
	}
	
	public static void print(int[][] sqa, int row, int col, int len) {
		System.out.println("Square is: row: " + row + ", col: " + col + ", len: " + len);
		for(int j = col; j < col + len; j++) {
			for(int i = row; i < row + len; i++) {
				System.out.print(sqa[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static void buildDP(int[][] square, Cell[][] dp) {
		for(int i = 0; i < square.length; i++) {
			for(int j = 0; j < square.length; j++) {
				if(square[i][j] == 0) dp[i][j] = new Cell(0, 0);
				else {
					dp[i][j] = new Cell(j - 1 >= 0 ? dp[i][j - 1].rowOffset + 1 : 1, i - 1 >= 0 ? dp[i - 1][j].colOffset + 1 : 1);
				}
			}
		}
	}
	
	public static class Cell {
		int rowOffset, colOffset;
		public Cell(int r, int c) {
			this.rowOffset = r;
			this.colOffset= c;
		}
	}
	
	
	
	public static void main(String[] args) {
		int[][] matrix = {
				{0, 1, 0},
				{1, 1, 0},
				{1, 1, 0}
		};
		maxSquare(matrix);

	}

}
