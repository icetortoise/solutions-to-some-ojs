package LongestSubWithoutRep;

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int start = -1, end = -1;
	int longest = 0;
	HashMap<Character, Integer> lastSeen = new HashMap<Character, Integer>();
	for(int i = 0; i<s.length(); i++){
	    if(lastSeen.containsKey(s.charAt(i)) && lastSeen.get(s.charAt(i)) > start){
		start = lastSeen.get(s.charAt(i));
		end = i;
		lastSeen.put(s.charAt(i), i);
	    }else{
		end++;
		int len = end - start;
		if(len > longest) longest = len;
		lastSeen.put(s.charAt(i), i);
	    }
	}
	return longest;
    }
}
