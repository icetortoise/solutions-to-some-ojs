package RotateImage;

public class Solution {
    public void rotate(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int nRow = matrix.length;
	if(nRow == 0) return;
	for(int i = 0; i <= (nRow%2==0?(nRow-1)/2:(nRow/2-1)); i++){
	    for(int j = 0; j <= (nRow%2==0?(nRow-1)/2:nRow/2); j++){
		int temp = matrix[j][nRow-i-1];
		matrix[j][nRow-i-1] = matrix[i][j];
		matrix[i][j] = temp;

		temp = matrix[nRow-1-i][nRow-j-1];
		matrix[nRow-1-i][nRow-j-1] = matrix[i][j];
		matrix[i][j] = temp;
		
		temp = matrix[nRow-1-j][i];
		matrix[nRow-1-j][i] = matrix[i][j];
		matrix[i][j] = temp;
	    }
	}
    }
}
