package Anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Solution {
    public ArrayList<String> anagrams(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
	HashMap<String, ArrayList<Integer>> anas = new HashMap<String, ArrayList<Integer>>();
	for (int i = 0; i < strs.length; i++){
	    char[] ca = strs[i].toCharArray();
	    Arrays.sort(ca);
	    String key = new String(ca);
	    if(!anas.containsKey(key)){
		ArrayList<Integer> idxs = new ArrayList<Integer>();
		idxs.add(i);
		anas.put(key, idxs);
	    }else{
		anas.get(key).add(i);
	    }
	}
	ArrayList<Integer> idxs = new ArrayList<Integer>();
	for(String k : anas.keySet()){
	    if(anas.get(k).size() > 1){
		idxs.addAll(anas.get(k));
	    }
	}
	ArrayList<String> result = new ArrayList<String>();
	for(int i : idxs){
	    result.add(strs[i]);
	}
	return result;
    }
}
