package PlusOne;
import java.util.ArrayList;
public class Solution {
    public int[] plusOne(int[] digits) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if(digits.length == 0) return (new int[]{1});
	boolean carry = true;
	ArrayList<Integer> result = new ArrayList<Integer>();
	for(int i = digits.length - 1; i >=0; i--){
	    if(carry){
		int x = digits[i] + 1;
		if(x/10 > 0){
		    digits[i] = x%10;
		}else{
		    digits[i] = x;
		    carry = false;
		}
	    }else{
		break;
	    }
	}
	if(carry){
	    int[] ok = new int[digits.length+1];
	    for(int i = 0; i < digits.length; i++){
		ok[i+1] = digits[i];
	    }
	    ok[0] = 1;
	    return ok;
	}else{
	    return digits;
	}
    }
}
