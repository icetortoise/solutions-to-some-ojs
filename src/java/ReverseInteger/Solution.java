package ReverseInteger;

import java.lang.Math;

public class Solution {
    public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
	if (x > -10 && x < 10){return x;}
	boolean pos = x >= 0;
	int absx = Math.abs(x);
	int digits = (int)Math.floor(Math.log10(absx));
	int y = absx % 10;
	int xRemain = absx / 10;
	int absResult = (int)(y*Math.pow(10,digits)) + reverse(xRemain);
	return pos?absResult:-absResult;
    }
}
