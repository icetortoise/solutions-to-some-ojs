package MinimumPathSum;

public class Solution {
    public int minPathSum(int[][] grid) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if (grid.length == 0) return 0;
	int m = grid.length, n = grid[0].length;
	int[][] mat = new int[m][n];
	for(int i = m-1; i>=0; i--){
	    for(int j = n-1; j>=0; j--){
		if(i == m-1 && j == n - 1){
		    mat[i][j] = grid[i][j];
		}else if(i == m-1){
		    mat[i][j] = grid[i][j] + mat[i][j+1];
		}else if(j == n-1){
		    mat[i][j] = grid[i][j] + mat[i+1][j];
		}else{
		    mat[i][j] = grid[i][j] + (mat[i][j+1] < mat[i+1][j]?mat[i][j+1]:mat[i+1][j]);
		}
	    }
	}
	return mat[0][0];
    }
}
