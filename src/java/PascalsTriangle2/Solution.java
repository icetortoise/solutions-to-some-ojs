
package PascalsTriangle2;

import java.math.BigInteger;
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> getRow(int rowIndex) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<Integer> result = new ArrayList<Integer>();
	int n = rowIndex;
	for(int i = 0; i<=n; i++){
	    if(i <= n/2){
		BigInteger nom = BigInteger.valueOf(1), dinom = BigInteger.valueOf(1);
		for(int j = 1; j <= i; j++){
		    dinom = dinom.multiply(BigInteger.valueOf(j));
		    nom = nom.multiply(BigInteger.valueOf(n - j + 1));
		}
		result.add((nom.divide(dinom)).intValue());
	    }else{
		result.add(result.get(n - i));
	    }
	}
	return result;
    }
}
