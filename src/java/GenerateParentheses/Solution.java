package GenerateParentheses;

import java.util.ArrayList;

public class Solution {
    public ArrayList<String> generateParenthesis(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	return generateParensImpl(n, 0, 0, "");
    }

    private ArrayList<String> generateParensImpl(int n, int l, int r, String s){
	ArrayList<String> result = new ArrayList<String>();
	if(l == n && r == n){
	    result.add(s);
	    return result;
	}
	if(l < n && l == r){
	    result.addAll(generateParensImpl(n, l + 1, r, s + "("));
	}else if(l < n && l > r){
	    result.addAll(generateParensImpl(n, l + 1, r, s + "("));
	    result.addAll(generateParensImpl(n, l, r + 1, s + ")"));
	}else if(l == n && l > r){
	    result.addAll(generateParensImpl(n, l, r + 1, s + ")"));
	}
	return result;
    }
}
