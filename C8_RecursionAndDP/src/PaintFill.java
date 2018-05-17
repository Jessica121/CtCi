import java.util.Arrays;

public class PaintFill {

	public static void main(String[] args) {
		Color[][] matrix = {
				{Color.RED, Color.RED, Color.GREEN, Color.RED},
				{Color.BLUE, Color.GREEN, Color.GREEN, Color.RED},
				{Color.RED, Color.RED, Color.GREEN, Color.RED},
				{Color.BLUE, Color.RED, Color.RED, Color.RED},
		};
		paintFill(matrix, 1, 0, Color.RED);
		for(Color[] row : matrix) System.out.println(Arrays.toString(row));
		
	}
	
	public static enum Color {
		RED, GREEN, BLUE;
	}
	
	private static void paintFill(Color[][] board, int row, int col, Color newColor) {
		if(board == null || board.length == 0 || outOfBounds(board, row, col) || board[row][col] == newColor) return;
		paintFill(board, row, col, newColor, board[row][col]);
	}
	
	private static void paintFill(Color[][] board, int row, int col, Color newColor, Color oldColor) {
		if(outOfBounds(board, row, col) || board[row][col] != oldColor) return;
		board[row][col] = newColor;
		paintFill(board, row + 1, col, newColor, oldColor);
		paintFill(board, row - 1, col, newColor, oldColor);
		paintFill(board, row, col + 1, newColor, oldColor);
		paintFill(board, row, col - 1, newColor, oldColor);
	}
	
	private static boolean outOfBounds(Color[][] board, int row, int col) {
		return row < 0 || col < 0 || row >= board.length || col >= board[0].length;
	}

}
