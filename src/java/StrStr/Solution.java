package StrStr;

public class Solution {
    public String strStr(String haystack, String needle) {
        // Start typing your Java solution below
        // DO NOT write main() function
	for(int i = 0; i<haystack.length() - needle.length() + 1; i++){
	    if(haystack.substring(i, i + needle.length()).equals(needle)){
		return haystack.substring(i);
	    }
	}
	return null;
    }
}
