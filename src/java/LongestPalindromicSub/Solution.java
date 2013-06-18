package LongestPalindromicSub;

public class Solution {
    public String longestPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int longest = 0;
	int n = s.length();
	if(n == 0){
	    return s;
	}
	int[] sub = new int[2];
	for(int i = 0 ; i <= 2*(n-1); i++){
	    int[] range = palindromeAt(s, i);
	    if(range[1] - range[0] + 1 > longest){
		longest = range[1] - range[0] + 1;
		sub = range;
	    }
	}
	return s.substring(sub[0], sub[1] + 1);
    }
    
    private int[] palindromeAt(String s, int idx){
	int start = 0, end = 0;
	int n = s.length();
	if(idx % 2 == 0){
	    start = end = idx / 2;
	}else{
	    start = idx / 2;
	    end = start + 1;
	}
	while(start >= 0 && end <= n - 1){
	    if(s.charAt(start) == s.charAt(end)){
		start--; end++;
	    }else{
		start++; end--;
		break;
	    }
	}
	if(start < 0 || end > n - 1){
	    start++; end--;
	}
	return new int[]{start, end};
    }
}
