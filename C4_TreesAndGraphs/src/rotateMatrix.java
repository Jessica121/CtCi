
public class rotateMatrix {
    public static int[][] rotate(int[][] matrix, int flag)
    {
        if(matrix == null || matrix.length == 0) return matrix;
        int m = matrix.length, n = matrix[0].length;
        int[][] res = convertM(n,m,matrix);
        res = flag == 1 ? rightRotate(res,n,m):leftRotate(res,n,m);
        return res;
    }
    
    public static int[][] convertM(int m,int n,int[][] matrix){
    		int[][] res = new int[n][m];
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++) res[j][i] = matrix[i][j];
        }
        return res;
    }
    
    public static int[][] rightRotate(int[][]res, int n, int m){
	    	for(int i = 0; i < n; i++)
	        {
	            for(int j = 0; j < m / 2; j++)
	            {
	                int tmp = res[i][j];
	                res[i][j] = res[i][m - 1 - j];
	                res[i][m - 1 - j] = tmp;
	            }
	        }
	    	return res;
    }
    
    public static int[][] leftRotate(int[][] res, int n, int m){
	    	for(int j = 0; j < m ;j++)
	        {
	            for(int i = 0; i < n / 2; i++)
	            {
	                int tmp = res[i][j];
	                res[i][j] = res[n - 1 - i][j];
	                res[n - 1 - i][j] = tmp;
	            }
	        }
	    	return res;
    }

//    not needed
    public static void printMatrix(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++) System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
    }
    
    public static void main(String[] args)
    {
        int[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] output1 = rotate(input, 0);
        int[][] output2 = rotate(input, 1);
        System.out.println("input");
        printMatrix(input);
  
        System.out.println("output 0-> left rotate");
        printMatrix(output1);
        System.out.println("output 1-> right rotate");
        printMatrix(output2);
    }


}
