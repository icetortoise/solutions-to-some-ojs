package PascalsTriangle;
import java.util.ArrayList;
import java.math.BigInteger;
public class Solution {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        // Start typing your Java solution below
        // DO NOT write main() function
	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	for(int i = 0; i < numRows; i++){
	    result.add(generateRow(i));
	}
	return result;
    }
    public ArrayList<Integer> generateRow(int n){
	ArrayList<Integer> result = new ArrayList<Integer>();
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
