import java.util.ArrayList;
import java.util.List;

public class RobotInAGrid {
	/*
	 * Given a boolean matrix, with false can't step on and true can, 
	 * decide if a robot can make its way from top left to bottom right.
	 * and return its path if it can.
	 * 
	 * */
	static int cnt = 0;

	public static void main(String[] args) {
		boolean[][] matrix = {
				{true, true, true, false, true, false, true, false},
				{true, true, true, true, true, false, true, false},
				{true, false, true, true, true, false, true, false},
				{true, false, true, false, true, false, true, false},
				{true, false, true, false, true, true, true, false},
				{true, false, true, false, true, false, true, false},
				{true, false, true, false, true, false, true, true},
		};
		System.out.println(canReach(matrix) + " " + cnt);
	}
	
	public static List<String> canReach(boolean[][] matrix) {
		List<String> res = new ArrayList<>();
		boolean[][] memo = new boolean[matrix.length][matrix[0].length]; 
		return check(matrix, 0, 0, res, memo) ? res : new ArrayList<>();
	}
	
	
	private static boolean check(boolean[][] matrix, int row, int col, List<String> list, boolean[][] memo) {
		if(row >= matrix.length || col >= matrix[0].length || !matrix[row][col]) return false;
		if(memo[row][col] == true) return true;
		cnt++;
		boolean hasReached = (row == matrix.length - 1 && col == matrix[0].length - 1);
		if(hasReached || check(matrix, row + 1, col, list, memo) || check(matrix, row, col + 1, list, memo)) {
			memo[row][col] = true;
			list.add(new String("(" + row + ", " + col + ")"));
			return true;
		}
		return false;
	}

}
