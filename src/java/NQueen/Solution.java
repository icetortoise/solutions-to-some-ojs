package NQueen;

import java.util.ArrayList;

public class Solution {
    public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(n == 0) {return new ArrayList<String[]>();}
	boolean[] cols = new boolean[n];
	boolean[] diagOne = new boolean[2*n-1];
	boolean[] diagTwo = new boolean[2*n-1];
	StringBuilder row = new StringBuilder();
	for(int i = 0; i < n; i++)
	    row.append('.');
	return solveNQueensImpl(n, 0, cols, diagOne, diagTwo, row.toString().toCharArray());
    }
    public ArrayList<String[]> solveNQueensImpl(int n, int l, 
						boolean[] cols, boolean[] diagOne, boolean[] diagTwo,
						char[] row){
	ArrayList<String[]> result = new ArrayList<String[]>();
	if(l == n-1){
	    for(int i = 0; i<n; i++){
		if(cols[i] || diagOne[l+i] || diagTwo[l - i + n -1]){
		    continue;
		}else{
		    char[] rowC = row.clone();
		    rowC[i] = 'Q';
		    String[] layout = new String[n];
		    layout[l] = new String(rowC);
		    result.add(layout);
		    return result;
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
		    char[] rowC = row.clone();
		    rowC[i] = 'Q';
		    colsC[i] = true;
		    diagOneC[l+i] = true;
		    diagTwoC[l - i + n -1] = true;
		    ArrayList<String[]> below = solveNQueensImpl(n, l+1, colsC, diagOneC, diagTwoC, row);
		    for (String[] layout : below){
			layout[l] = new String(rowC);
		    }
		    result.addAll(below);
		}
	    }
	}
	return result;
    }
}
