package LetterCombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
        HashMap<String, String[]> dict = new HashMap<String, String[]>();
	dict.put("2", new String[] {"a","b","c"});
	dict.put("3", new String[] {"d","e","f"});
	dict.put("4", new String[] {"g","h","i"});
	dict.put("5", new String[] {"j","k","l"});
	dict.put("6", new String[] {"m","n","o"});
	dict.put("7", new String[] {"p","q","r","s"});
	dict.put("8", new String[] {"t","u","v"});
	dict.put("9", new String[] {"w","x","y","z"});
	dict.put("0", new String[] {" "});
	if(digits.length() == 0){
	    ArrayList<String> result = new ArrayList<String>();
	    result.add("");
	    return result;
	}
	if(digits.length() == 1){
	    return new ArrayList<String>(Arrays.asList(dict.get(digits.substring(0,1))));
	}else{
	    String[] f = dict.get(digits.substring(0,1));
	    ArrayList<String> comb = letterCombinations(digits.substring(1));
	    ArrayList<String> result = new ArrayList<String>();
	    for(int i = 0; i < f.length; i++){
		for(String x : comb){
		    result.add(f[i] + x);
		}
	    }
	    return result;
	}
    }
}
