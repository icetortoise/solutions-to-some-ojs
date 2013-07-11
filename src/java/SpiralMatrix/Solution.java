package SpiralMatrix;

import java.util.ArrayList;

public class Solution {
    public enum Direction {
	UP, DOWN, LEFT, RIGHT
    }

    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result = new ArrayList<Integer>();
	if(matrix.length == 0 || matrix[0].length == 0)
	    return result;
	int i = 0, j = 0;
	Direction d = Direction.RIGHT;
	int up = 1, down = matrix.length-1, left = 0, right = matrix[0].length - 1;
	while(true){
	    switch(d){
	    case RIGHT : 
		if(j > right){
		    return result;
		}
		result.add(matrix[i][j]);
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
		result.add(matrix[i][j]);
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
		result.add(matrix[i][j]);
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
		result.add(matrix[i][j]);
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
