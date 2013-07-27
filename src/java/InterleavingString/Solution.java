package InterleavingString;

public class Solution {


    public boolean isInterleave(String s1, String s2, String s3) {
	int m = s1.length(), n = s2.length();
	if(s3.length() != m+n)
	    return false;
	boolean[][] dp = new boolean[m+1][n+1];
	dp[0][0] = true; // case of "" vs ""
	for(int i = 0; i <= m; i++){
	    for(int j = 0; j <= n; j++){
		if(dp[i][j]
		   || (i >= 1 && dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i + j - 1)) 
		   || (j >= 1 && dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i + j - 1))){
		    dp[i][j] = true;
		}else{
		    dp[i][j] = false;
		}
	    }
	}
	return dp[m][n];
    }


    // too many repitition!
    public boolean isInterleaveRec(String s1, String s2, String s3) {
        // Start typing your Java solution below
        // DO NOT write main() function
	return interleavImpl(s1, s2, s3, true) || interleavImpl(s1, s2, s3, false);
    }



    //(.isInterleave s "ab" "dd" "adbb")    
    public boolean interleavImpl(String s1, String s2, String s3, boolean startFromOne) {
	if(s1.length() == 0 && s2.length() == 0 && s3.length() == 0)
	    return true;
	if(s3.length() != s1.length() + s2.length())
	    return false;
	boolean match = false;
	int i = 0;
	if(startFromOne){
	    while(i < s1.length()){
		if(s1.charAt(i) == s3.charAt(i)){
		    match = true;
		    if(interleavImpl(s1.substring(i+1), s2, s3.substring(i+1), false)){
			return true;
		    }
		}else{
		    break;
		}
		i++;
	    }
	    if(!match) return false;
	}else{
	    while(i < s2.length()){
		if(s2.charAt(i) == s3.charAt(i)){
		    match = true;
		    if(interleavImpl(s1, s2.substring(i+1), s3.substring(i+1), true)){
			return true;
		    }
		}else{
		    break;
		}
		i++;
	    }
	    if(!match) return false;
	}
	return false;//never reach?
    }
}
