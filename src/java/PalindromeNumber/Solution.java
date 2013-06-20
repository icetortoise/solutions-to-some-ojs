package PalindromeNumber;

public class Solution {
    public boolean isPalindrome(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if (x<0) return false;
	if (x == 0) return true;
	int digits = (int)Math.floor(Math.log10(x));
	int i = 1;
	while(i <= digits){
	    if ((int)((x%(int)Math.pow(10,i))/Math.pow(10,i-1)) == (x/(int)Math.pow(10,digits))%10){
		i++; digits--;
	    }else{
		return false;
	    }
	}
	return true;
    }
}
