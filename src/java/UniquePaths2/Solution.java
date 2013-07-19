package UniquePaths2;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(obstacleGrid.length == 0) return 0;
	int m = obstacleGrid.length, n = obstacleGrid[0].length;
	int[][] mat = new int[m][n];
	for(int i = m - 1; i >= 0 ; i--){
	    for(int j = n - 1; j >= 0; j--){
		if(obstacleGrid[i][j] == 1){
		    mat[i][j] = 0;
		}else if(i == m - 1 && j == n - 1){
		    mat[i][j] = 1;		    
		}else if(i == m - 1){
		    mat[i][j] = mat[i][j+1];
		}else if(j == n - 1){
		    mat[i][j] = mat[i+1][j];
		}else{
		    mat[i][j] = mat[i+1][j] + mat[i][j+1];
		}
	    }
	}
	return mat[0][0];
    }

    public int uniquePathsImpl(int[][] obstacleGrid, int i, int j) {
	if(obstacleGrid[i][j] == 1){
	    return 0;
	}
	if(i == 0 && j == 0){
	    return 1;
	}else if(i == 0){
	    return uniquePathsImpl(obstacleGrid, i, j-1);
	}else if(j == 0){
	    return uniquePathsImpl(obstacleGrid, i-1, j);
	}else{
	    return uniquePathsImpl(obstacleGrid, i-1, j) + 
		uniquePathsImpl(obstacleGrid, i, j-1);
	}
    }
}
