package MaximunRectangle;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(matrix.length == 0) return 0;
	if(matrix[0].length == 0) return 0;
	int m=matrix.length, n=matrix[0].length;
	int [][] lm = new int[m][n];
	for(int j = 0; j < n; j++){
	    for(int i = m -1; i >=0; i--){
		if(matrix[i][j] == '0'){
		    lm[i][j] = 0;
		}else{
		    if(i+1<=m-1){
			lm[i][j] = lm[i+1][j] + 1;
		    }else{
			lm[i][j] = 1;
		    }
		}
	    }
	}
	int area = 0;
	for(int i = 0; i < m; i++){
	    int newArea = largestRectangleArea(lm[i]);
	    if(newArea > area){
		area = newArea;
	    }
	}
	return area;
    }

    public int largestRectangleArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(height.length == 0) return 0;
	int[] left = new int[height.length], right = new int[height.length];
	fillLeft(height, left);
	fillRight(height, right);
	int area = 0;
	for(int i = 0; i<height.length;i++){
	    int newArea = height[i] * (right[i] - left[i] + 1);
	    if(newArea > area)
		area = newArea;
	}
	return area;
    }

    public void fillLeft(int[] height, int[] left){
	for(int i = 0; i < height.length; i++){
	    int j = i -1;
	    while(true){
		if(j<0){
		    left[i] = 0;
		    break;
		}else{
		    if(height[i] == height[j]){
			left[i] = left[j];
			break;
		    }else if(height[i] > height[j]){
			left[i] = j+1;
			break;
		    }else{
			j = left[j] - 1;
			continue;
		    }
		}
	    }
	}
    }

    public void fillRight(int[] height, int[] right){
	for(int i = height.length - 1; i >= 0; i--){
	    int j = i + 1;
	    while(true){
		if(j>= height.length){
		    right[i] = height.length - 1;
		    break;
		}else{
		    if(height[i] == height[j]){
			right[i] = right[j];
			break;
		    }else if(height[i] > height[j]){
			right[i] = j - 1;
			break;
		    }else{
			j = right[j] + 1;
			continue;
		    }
		}
	    }
	}
    }

}
