package PermutationSequence;

import java.util.ArrayList;

public class Solution {
    public String getPermutation(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int[] fact = new int[n];
	fact[0] = 1;
	for(int i = 1; i < n; i++){
	    fact[i] = fact[i-1] * (i + 1);
	}
	ArrayList<Integer> items = new ArrayList<Integer>();
	for(int i = 0; i < n; i++){
	    items.add(i + 1);
	}
        StringBuilder sb = new StringBuilder();
	getPermutationImpl(items, k, fact, sb);
	return sb.toString();
    }

    public void getPermutationImpl(ArrayList<Integer> items, int k, int[] fact, StringBuilder sb){
	if(k < fact[0] || k > fact[items.size() - 1]){
	    return;
	}
	if(items.size() == 1){
	    sb.append(items.get(k - 1));
	}else{
	    int idx = (k-1)/fact[items.size() - 2];
	    sb.append(items.get(idx));
	    items.remove(idx);
	    getPermutationImpl(items, (k-1)%fact[items.size() - 1] + 1 , fact, sb);
	}
    }
}
