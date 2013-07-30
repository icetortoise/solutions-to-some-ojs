package DistinctSubsequences;

public class Solution {
    public int numDistinct(String S, String T) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(S.length() == 0 || T.length() == 0){
	    return 0;
	}
	int m = T.length(), n = S.length();
	int[][] dp = new int[m][n];
	for(int i = 0; i < m; i++){
	    for(int j = i; j < n; j++){
		if(i == 0){
		    if(S.charAt(j) == T.charAt(i)){
			dp[i][j] = (j - 1>=0? dp[i][j-1] : 0) + 1;
		    }else{
			dp[i][j] = j - 1>=0? dp[i][j-1] : 0;
		    }
		}else{
		    int accu = dp[i][j-1];
		    int prev = dp[i-1][j-1];
		    if(S.charAt(j) == T.charAt(i)){
			dp[i][j] = accu + prev;
		    }else{
			dp[i][j] = accu;
		    }
		}
	    }
	}
	return dp[m-1][n-1];
    }
}
