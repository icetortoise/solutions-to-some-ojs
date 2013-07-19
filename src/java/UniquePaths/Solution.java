package UniquePaths;

import java.util.ArrayList;
import java.util.HashMap;
import java.math.BigInteger;

public class Solution {
    public int uniquePaths(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	BigInteger result = fact(m+n-2).divide(fact(m-1).multiply(fact(n-1)));
	return result.intValue();
    }
    
    public BigInteger fact (int m){
	if(m == 0){
	    return BigInteger.valueOf(1);
	}else{
	    return BigInteger.valueOf(m).multiply(fact(m-1));
	}
    }

    public int uniquePathsImpl(int m, int n, HashMap<ArrayList<Integer>, Integer> lookup){
	if(m == 1 || n == 1){
	    return 1;
	}else{
	    ArrayList<Integer> key = new ArrayList<Integer>();
	    if(m-1 < n){
		key.add(m); key.add(n);
	    }else{
		key.add(n); key.add(m);
	    }
	    if(lookup.containsKey(key)){
		return lookup.get(key);
	    }else{
		int val = uniquePaths(m-1, n) + uniquePaths(m, n-1);
		lookup.put(key, val);
		return val;
	    }
	}
    }
}
