package CuttingSticks;

import java.io.*;
import java.util.*;

class Main
{
    static String ReadLn (int maxLg)  // utility function to read from stdin
    {
        byte lin[] = new byte [maxLg];
        int lg = 0, car = -1;
        String line = "";

        try
        {
            while (lg < maxLg)
            {
                car = System.in.read();
                if ((car < 0) || (car == '\n')) break;
                lin [lg++] += car;
            }
        }
        catch (IOException e)
        {
            return (null);
        }

        if ((car < 0) && (lg == 0)) return (null);  // eof
        return (new String (lin, 0, lg));
    }

    public static void main (String args[])  // entry point from OS
    {
        Main myWork = new Main();  // create a dinamic instance
        myWork.Begin();            // the true entry point
    }

    void Begin()
    {
	while(true){
	    int l = Integer.valueOf(ReadLn(10));
	    if(l == 0){
		return;
	    }
	    int ncut = Integer.valueOf(ReadLn(10));
	    String[] cuts = ReadLn(1000).split("\\s");
	    if(ncut == 0){
		System.out.println("The minimum cutting is 0.");
		return;
	    }
	    if(cuts.length != ncut){
		System.out.println("err");
		return;
	    }
	    int[] icuts = new int[ncut];
	    for(int i = 0; i < ncut; i++){
		icuts[i] = Integer.valueOf(cuts[i]);
	    }
	    process(l, ncut, icuts);
	}
    }
    
    void process(int len, int ncut, int[] cuts){
	int nseg = ncut + 1;
	int[][] dp = new int[nseg][nseg];
	int[] segStart = new int[nseg+1];
	segStart[segStart.length-1] = len;
	for(int i = 1; i<segStart.length-1; i++){
	    segStart[i] = cuts[i-1];
	}
	// for(int i = 0; i < nseg; i++){
	//     for(int j = 0; j < nseg; j++){
	// 	dp[0][j] = 0;
	//     }
	// }
	for(int i = 1; i < nseg; i++){
	    for(int j = 0; j < nseg - i; j++){
		// we are dealing with a seg that has unit cut of (i+1) and start from segStart[j]
		int cutLen = segStart[j+i+1] - segStart[j];
		int minSubLen = Integer.MAX_VALUE;
		for(int c = j + 1; c <= j + i; c++){
		    int subLen = dp[c - j - 1][j] + dp[j+i - c][c];
		    if(subLen < minSubLen){
			minSubLen = subLen;
		    }
		}
		dp[i][j] = cutLen + minSubLen;
	    }
	}
	System.out.println("The minimum cutting is " + dp[nseg-1][0] + ".");
    }
}
