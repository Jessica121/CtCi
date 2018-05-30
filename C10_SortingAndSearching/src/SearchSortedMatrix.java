
public class SearchSortedMatrix {
	/*
	 * find the submatrix all smaller and all bigger than the target by binary search the diagonal, 
	 * and recursively find from the rest of the two submatrix.
	 * pass in origin, end, if out of bounds / origin > end return;
	 * if origin == end && origin == target return target
	 * partition can be put into a separate function.
	 * 
	 * */
	public static Coordinate findSortedMatrix(int[][] matrix, int target) {
		Coordinate start = new Coordinate(0, 0), end = new Coordinate(matrix.length - 1, matrix[0].length - 1);
		return findMatrix(matrix, target, start, end);
	}
	
	private static Coordinate findMatrix(int[][] matrix, int target, Coordinate start, Coordinate end) {
		if(start.outOfBounds(matrix) || end.outOfBounds(matrix) || !start.isBefore(end)) return null;
		if(start.equals(end)) {
			if(matrix[start.row][start.col] == target) return start;
			else return null;
		}
		Coordinate origin = start.clone(), mid = new Coordinate(-1, -1);
		// calculate the diagonal, not directly the end.
		int diaDist = Math.min(end.row - start.row, end.col - start.col);
		Coordinate dest = new Coordinate(origin.row + diaDist, origin.col + diaDist);
		/* Binary search on the diagonal. */
		
		/*
		 * 		{15, 20, 70, 85},
				{20, 35, 80, 95},
				{30, 55, 95,105},
//				{40, 80,100,120}
				
		 * */
		while(start.isBefore(dest)) {
			mid = start.getMid(dest);
			if(matrix[mid.row][mid.col] == target) return mid;
			else if(matrix[mid.row][mid.col] > target) {
				dest.row = mid.row - 1;
				dest.col = mid.col - 1;
			} else {
				start.row = mid.row + 1;
				start.col = mid.col + 1;
			}
		}
		return partition(matrix, origin, end, start, target);
	}
	
	private static Coordinate partition(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int target) {
		Coordinate leftStart = new Coordinate(pivot.row, origin.col), leftEnd = new Coordinate(dest.row, pivot.col - 1);
		Coordinate rightStart = new Coordinate(origin.row, pivot.col), rightEnd = new Coordinate(pivot.row - 1, dest.col);
		Coordinate leftPart = findMatrix(matrix, target, leftStart, leftEnd);
		if(leftPart != null) return leftPart;
		Coordinate rightPart = findMatrix(matrix, target, rightStart, rightEnd);
		return rightPart;
	}
	
	public static class Coordinate {
		int row, col;
		public Coordinate(int r, int c) {
			this.row = r;
			this.col = c;
		}
		public boolean isBefore(Coordinate c) {
			return this.row <= c.row && this.col <= c.col;
		}
		public boolean outOfBounds(int[][] matrix) {
			return this.row < 0 || this.col < 0 || this.row >= matrix.length || this.col >= matrix[0].length;
		}
		public boolean equals(Coordinate c) {
			return this.row == c.row && this.col == c.col;
		}
		public Coordinate getMid(Coordinate c) {
			return new Coordinate((this.row + c.row) / 2, (this.col + c.col) / 2);
		}
		
		public Coordinate clone() {
			return new Coordinate(this.row, this.col);
		}
		
		@Override
		public String toString() {
			return this.row + ", " + this.col;
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{15, 20, 70, 85},
				{20, 35, 80, 95},
				{30, 55, 95,105},
				{40, 80,100,120}
		};
		Coordinate res = findSortedMatrix(matrix, 945);
		System.out.println(res.toString());
	}

}
