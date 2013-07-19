package Subsets;

import java.util.*;

public class Solution {
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if(S.length == 0) return new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>();
	cache.add(new ArrayList<Integer>());
	Arrays.sort(S);
	return subsetsImpl(S, 0, cache);
    }

    public ArrayList<ArrayList<Integer>> subsetsImpl(int[] S, int idx, ArrayList<ArrayList<Integer>> cache) {
	if(idx == S.length){
	    return cache;
	}
	ArrayList<ArrayList<Integer>> newCache = new ArrayList<ArrayList<Integer>>(cache);
	for(ArrayList<Integer> set : cache){
	    ArrayList<Integer> temp = new ArrayList<Integer>(set);
	    temp.add(S[idx]);
	    newCache.add(temp);
	}
	return subsetsImpl(S, idx+1, newCache);
    }
}
