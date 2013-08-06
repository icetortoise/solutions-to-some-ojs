package WordLadder;

import java.util.*;
public class Solution {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Queue<String> q = new ArrayDeque<String>();
	q.offer(start);
	q.offer("#");
	int dis = 1;
	while(q.peek() != null){
	    String e = q.poll();
	    if("#".equals(e)){
		dis++;
		if(q.peek() != null){
		    q.offer("#");
		}
	    }else if(end.equals(e)){
		return dis;
	    }else{
		char[] chars = e.toCharArray();
		for(int i = 0; i<chars.length; i++){
		    for(char c = 'a' ; c <= 'z'; c++){
			if(c != chars[i]){
			    char[] newChars = (char[])chars.clone();
			    newChars[i] = c;
			    String newStr = new String(newChars);
			    if(dict.contains(newStr)){
				q.offer(newStr);
				dict.remove(newStr);
			    }
			}
		    }
		}
	    }
	}
	return 0;
    }
}
