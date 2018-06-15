
public class SecondPass_RotateImage {
	/*
    rotate base on anti diagional, then swap the first row, last row, second row, second last row, ...
    anti dia: row + col == len - 1 swap img[row][col], img[len - 1 - col][len - 1 - row]
    then swap img[row][col], img[len - 1 - row][col],
    */
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for(int i = 0; i < len - 1; i++) {
            for(int j = 0; j < len - 1 - i; j++) {
                swap(matrix, i, j, len - 1 - j, len - 1 - i);
            }
        }
        
        for(int i = 0; i < len / 2; i++) {
            // col has full length
            for(int j = 0; j < len; j++) {
                swap(matrix, i, j, len - 1 - i, j);
            }
        }
    }
    
    private void swap(int[][] matrix, int row1, int col1, int row2, int col2) {
        int temp = matrix[row1][col1];
        matrix[row1][col1] = matrix[row2][col2];
        matrix[row2][col2] = temp;
    }
}
