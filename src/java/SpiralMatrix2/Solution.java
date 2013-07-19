package SpiralMatrix2;

public class Solution {
    public enum Direction {
	UP, DOWN, LEFT, RIGHT
    }

    public int[][] generateMatrix(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int[][] result = new int[n][n];
	if(n == 0) return result;
	int i = 0, j = 0;
	Direction d = Direction.RIGHT;
	int up = 1, down = n-1, left = 0, right = n - 1;
	int val = 0;
	while(true){
	    val++;
	    switch(d){
	    case RIGHT : 
		if(j > right){
		    return result;
		}
		result[i][j] = val;
		if(j == right){
		    d = Direction.DOWN;
		    right--;
		    i++;
		}else{
		    j++;
		}
		break;
	    case DOWN:
		if(i > down){
		    return result;
		}
		result[i][j] = val;
		if(i == down){
		    d = Direction.LEFT;
		    down--;
		    j--;
		}else{
		    i++;
		}
		break;
	    case LEFT : 
		if(j < left){
		    return result;
		}
		result[i][j] = val;
		if(j == left){
		    d = Direction.UP;
		    left++;
		    i--;
		}else{ 
		    j--;
		}
		break;
	    case UP:
		if(i < up){
		    return result;
		}
		result[i][j] = val;
		if(i == up){
		    d = Direction.RIGHT;
		    up++;
		    j++;
		}else{
		    i--;
		}
		break;
	    }
	}
    }
}
