package UnidirectionalTSP;

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
	    String s = ReadLn(500);
	    if(s == null || s.length()==0){
		break;
	    }
	    String[] r = s.split("\\s");
	    int nrow = Integer.valueOf(r[0]);
	    int ncol = Integer.valueOf(r[1]);
	    process(nrow, ncol);
	}
    }
    
    void process(int nrow, int ncol){
	int[][] dp = new int[nrow][ncol];
	int[][] pre = new int[nrow][ncol];
	// not implemented, therefore it will not return lexicographically minimun path...got WA
	int[][] order = new int[nrow][ncol]; // maintain the lexicographically min order of the path to order[i][j] that mininize the pathW too.
	int[][] data = new int[nrow][ncol];
	for(int i = 0; i<nrow; i++){
	    String[] s = ReadLn(500).split("\\s");
	    if(s.length != ncol){
		System.out.println("err");
		return;
	    }
	    for(int j = 0; j < ncol; j++){
		data[i][j] = Integer.valueOf(s[j]);
	    }
	    dp[i][0] = data[i][0];
	    pre[i][0] = -1;
	    order[i][0] = i;
	}
	int lastIdx = -1;
	int pathW = Integer.MAX_VALUE;
	for(int j = 1; j < ncol; j++){
	    for(int i = 0; i < nrow; i++){
		int i1 = (i - 1)%nrow;
		if(i1 < 0){
		    i1 += nrow;
		}
		int i2 = (i + 1)%nrow;
		if(dp[i1][j-1] < dp[i2][j-1]){
		    if(dp[i1][j-1] < dp[i][j-1]){
			dp[i][j] = dp[i1][j-1] + data[i][j];
			pre[i][j] = i1;
		    }else if(dp[i1][j-1] == dp[i][j-1] && i1 < i){
			dp[i][j] = dp[i1][j-1] + data[i][j];
			pre[i][j] = i1;
		    }else{
			dp[i][j] = dp[i][j-1] + data[i][j];
			pre[i][j] = i;
		    }
		}else if(dp[i1][j-1] > dp[i2][j-1]){
		    if(dp[i2][j-1] < dp[i][j-1]){
			dp[i][j] = dp[i2][j-1] + data[i][j];
			pre[i][j] = i2;
		    }else if(dp[i2][j-1] == dp[i][j-1] && i2 < i){
			dp[i][j] = dp[i2][j-1] + data[i][j];
			pre[i][j] = i2;
		    }else{
			dp[i][j] = dp[i][j-1] + data[i][j];
			pre[i][j] = i;
		    }
		}else{
		    int temp = -1;
		    if(i1 < i2){
			temp = i1;
		    }else{
			temp = i2;
		    }
		    if(dp[temp][j-1] < dp[i][j-1]){
			dp[i][j] = dp[temp][j-1] + data[i][j];
			pre[i][j] = temp;
		    }else if(dp[temp][j-1] == dp[i][j-1] && temp < i){
			dp[i][j] = dp[temp][j-1] + data[i][j];
			pre[i][j] = temp;
		    }else{
			dp[i][j] = dp[i][j-1] + data[i][j];
			pre[i][j] = i;
		    }
		}
		if(j == ncol - 1 && dp[i][j] < pathW){
		    pathW = dp[i][j];
		    lastIdx = i;
		}
	    }
	}
	ArrayList<Integer> path = new ArrayList<Integer>();
	while(lastIdx != -1){
	    path.add(0, lastIdx+1);
	    lastIdx = pre[lastIdx][ncol-1];
	    ncol--;
	}
	for(int i = 0; i < path.size(); i++){
	    if(i == 0){
		System.out.printf(Integer.toString(path.get(i)));
	    }else{
		System.out.printf(" " + Integer.toString(path.get(i)));
	    }
	}
	System.out.println();
	System.out.println(pathW);
    }
}
