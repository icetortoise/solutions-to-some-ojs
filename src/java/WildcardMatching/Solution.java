package WildcardMatching;

import java.util.*;

public class Solution {
    public boolean isMatch(String s, String p) {
        // Start typing your Java solution below
        // DO NOT write main() function
	char[] pa = p.toCharArray();
	int count = 0;
	for(int i = 0; i < pa.length; i++){
	    if(pa[i] != '*'){
		count++;
	    }
	}
	if(s.length() < count){
	    return false;
	}

	HashSet<Integer> set = new HashSet<Integer>();
	dfs(pa, 0, set);
	
	//match
	for(int i = 0; i < s.length(); i++){
	    HashSet<Integer> matches = new HashSet<Integer>();
	    for(int status : set){
		if(status >= pa.length)
		    continue;
		if(pa[status] == '*'){
		    addForStar(status, matches, pa);
		}
		else if( pa[status] == '?' || pa[status] == s.charAt(i)){
		    matches.add(status+1);
		    if(status + 1 < pa.length && pa[status+1] == '*'){
			addForStar(status+1, matches, pa);
		    }
		}
	    }
	    set = matches;
	}
	return set.contains(pa.length);
    }
    
    public void addForStar(int status, HashSet<Integer> matches, char[] pa){
	matches.add(status);
	matches.add(status+1);
	if(status+1 < pa.length && pa[status+1] == '*'){
	    addForStar(status+1, matches, pa);
	}
    }

    public void dfs(char[] pa, int status, HashSet<Integer> set){
	if(pa.length == 0){
	    set.add(0);
	    return;
	}
	if(status < pa.length && pa[status] == '*'){
	    dfs(pa, status+1, set);
	}
	set.add(status);
    }
}
