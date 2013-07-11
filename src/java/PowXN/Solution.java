package PowXN;

public class Solution {
    public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
	int an = Math.abs(n);
	double result = 1;
	while(an > 0){
	    result *= x;
	    if(result == 0 || result == 1){
		break;
	    }
	    else if(result == -1){
		if(an%2 == 0)
		    return 1;
		else
		    return -1;
	    }
	    an--;
	}
	if(n < 0){
	    return 1/result;
	}
	return result;
    }
}
