package DivideTwoIntegers;

public class Solution {
    public int divide(int dividend, int divisor) {
        // Start typing your Java solution below
        // DO NOT write main() function
        assert divisor != 0;
	boolean positive = false;
	if((dividend > 0 && divisor >0) || (dividend < 0 && divisor <0)){
	    positive = true;
	}
	int a = Math.abs(dividend), b = Math.abs(divisor);
	int i = 0;
	while(true){
	    a = a >> 1;
	    if(a >= b){i += 2;}
	    else{i++; break;}
	}
	return positive?2^i:-2^i;
    }
}
