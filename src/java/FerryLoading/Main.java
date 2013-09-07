package FerryLoading;

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
	int ncase = Integer.valueOf(ReadLn(10));
	ReadLn(10);
	for(int i = 1; i <= ncase; i++){
	    int len = Integer.valueOf(ReadLn(10).trim());
	    ArrayList<Integer> cars = new ArrayList<Integer>();
	    while(true){
		int carL = Integer.valueOf(ReadLn(10).trim());
		if(carL == 0){
		    break;
		}else{
		    cars.add(carL);
		}
	    }
	    ReadLn(10);
	    if(i != 1){
		System.out.println();
	    }
	    process(len*100, cars);
	}
    }
    
    void process(int len, ArrayList<Integer> cars){
	boolean[][] dp = new boolean[cars.size()][len+1];
	boolean[][] position = new boolean[cars.size()][len+1];
	int[][] pre = new int[cars.size()][len+1];
	dp[0][0] = true; position[0][0] = false;
	pre[0][0] = -1;
	if(cars.get(0) > len){
	    System.out.println(0);
	    return;
	}else{
	    dp[0][cars.get(0)] = true;
	    position[0][cars.get(0)] = true;
	    pre[0][cars.get(0)] = -1;
	}
	int accumLen = cars.get(0);
	int i = 1;
	for(; i < cars.size(); i++){
	    int c = cars.get(i);
	    boolean found = false;
	    for(int j = 0; j < len+1; j++){
		if(dp[i-1][j]){
		    int right = accumLen - j;
		    if(c <= len - right){
			dp[i][j] = true;
			position[i][j] = false;
			pre[i][j] = j;
			found = true;
		    }
		}else if(j-c >= 0 && dp[i-1][j-c]){
		    dp[i][j] = true;
		    position[i][j] = true;
		    pre[i][j] = j-c;
		    found = true;
		}
	    }
	    accumLen += c;
	    if(!found){
		break;
	    }
	}
	System.out.println(i);
	int k = 0;
	for(; k < len+1; k++){
	    if(dp[i-1][k]){
		break;
	    }
	}
	ArrayList<String> path = new ArrayList<String>();
	while(true){
	    if(position[i-1][k]){
		path.add(0, "port");
	    }else{
		path.add(0, "starboard");
	    }
	    k = pre[i-1][k];
	    i--;
	    if(k == -1){
		break;
	    }
	}
	for(String s : path){
	    System.out.println(s);
	}
    }
}
