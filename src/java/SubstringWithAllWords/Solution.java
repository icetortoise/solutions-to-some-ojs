package SubstringWithAllWords;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

// this solution does not pass OJ because i overlooked the condition that elements in L are of the same length. This is 
// actually a more general solution. 
// In particular, if L[i] are all of the same length, the operatin to judge if S[a..a+len] is a substring is constant!
// Potential improvement is around strStr, to use KMP or a suffix array!
public class Solution {

    public ArrayList<Integer> findSubstring(String S, String[] L) {
	HashMap<Integer, ArrayList<Integer>> idxToMatches = new HashMap<Integer, ArrayList<Integer>>();
	for(int i = 0; i < L.length; i++){
	    for(Integer idx : strStr(S, L[i])){
		if(idxToMatches.containsKey(idx)){
		    ArrayList<Integer> temp = idxToMatches.get(idx);
		    temp.add(i);
		    idxToMatches.put(idx, temp);
		}else{
		    ArrayList<Integer> n = new ArrayList<Integer>();
		    n.add(i);
		    idxToMatches.put(idx, n);
		}
	    }
	}
	ArrayList<Integer> result = new ArrayList<Integer>();
	for(Integer idx : idxToMatches.keySet()){
	    HashSet<Integer> c = new HashSet<Integer>();
	    if(validSubWithAllWords(idx, idxToMatches, L, c))
		result.add(idx);
	}
	return result;
    }

    public boolean validSubWithAllWords(int idx, HashMap<Integer, ArrayList<Integer>> idxToMatches, 
					String[] L, HashSet<Integer> c){
	if(c.size() == L.length){return true;}
	if(!idxToMatches.containsKey(idx)){return false;}
	for(Integer match : idxToMatches.get(idx)){
	    HashSet<Integer> newc = new HashSet<Integer>(c);
	    if(!newc.contains(match)){
		newc.add(match);
		if(validSubWithAllWords(idx + L[match].length(), idxToMatches, L ,newc)){
		    return true;
		}
	    }
	}
	return false;
    }

    
    public ArrayList<Integer> strStr(String haystack, String needle) {
	// Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result =  new ArrayList<Integer>();
	for(int i = 0; i<haystack.length() - needle.length() + 1; i++){
	    if(haystack.substring(i, i + needle.length()).equals(needle)){
		result.add(i);
	    }
	}
	return result;
    }

}
