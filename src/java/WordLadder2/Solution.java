package WordLadder2;

import java.util.*;

public class Solution {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        Queue<ArrayList<String>> q = new ArrayDeque<ArrayList<String>>();
	ArrayList<String> p = new ArrayList<String>();
	p.add(start);
	q.offer(p);
	int shortestSoFar = Integer.MAX_VALUE;
	while(q.peek() != null){
	    ArrayList<String> e = q.poll();
	    char[] chars = e.get(e.size()-1).toCharArray();
	    for(int i = 0; i<chars.length; i++){
		for(char c = 'a' ; c <= 'z'; c++){
		    if(c != chars[i]){
			char[] newChars = (char[])chars.clone();
			newChars[i] = c;
			String newStr = new String(newChars);
			if(end.equals(newStr)){
			    ArrayList<String> path = new ArrayList<String>(e);
			    path.add(newStr);
			    if(path.size() < shortestSoFar){
				result.clear();
				shortestSoFar = path.size();
				result.add(path);
			    }else if(path.size() == shortestSoFar){
				result.add(path);
			    }
			}else if(dict.contains(newStr)){
			    ArrayList<String> path = new ArrayList<String>(e);
			    if(path.size() < shortestSoFar){
				path.add(newStr);
				q.offer(path);
			    }
			}
		    }
		}
	    }

	}
	return result;
    }

}
