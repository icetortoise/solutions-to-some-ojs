package NQueen2;

import java.util.ArrayList;

public class Solution {
    private int count = 0;
    public int totalNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(n == 0) {return 0;}
	count = 0;
	boolean[] cols = new boolean[n];
	boolean[] diagOne = new boolean[2*n-1];
	boolean[] diagTwo = new boolean[2*n-1];
	StringBuilder row = new StringBuilder();
	solveNQueensImpl(n, 0, cols, diagOne, diagTwo);
	return count;
    }
    public void solveNQueensImpl(int n, int l, 
				boolean[] cols, boolean[] diagOne, boolean[] diagTwo){
	if(l == n-1){
	    for(int i = 0; i<n; i++){
		if(cols[i] || diagOne[l+i] || diagTwo[l - i + n -1]){
		    continue;
		}else{
		    count++;
		    return;
		}
	    }
	}else{
	    for(int i = 0; i<n; i++){
		if(cols[i] || diagOne[l+i] || diagTwo[l - i + n -1]){
		    continue;
		}else{
		    boolean[] colsC = cols.clone();
		    boolean[] diagOneC = diagOne.clone();
		    boolean[] diagTwoC = diagTwo.clone();
		    colsC[i] = true;
		    diagOneC[l+i] = true;
		    diagTwoC[l - i + n -1] = true;
		    solveNQueensImpl(n, l+1, colsC, diagOneC, diagTwoC);
		}
	    }
	}
    }
}
