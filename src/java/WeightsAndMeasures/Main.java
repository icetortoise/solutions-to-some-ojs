package WeightsAndMeasures;

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
	ArrayList<Turtle> tls = new ArrayList<Turtle>();
	int i = 0;
	while(true){
	    String s = ReadLn(100);
	    if(s == null || s.length()==0){
		break;
	    }
	    tls.add(new Turtle(Integer.valueOf(s.split(" ")[0]),
			       Integer.valueOf(s.split(" ")[1])));
	}
	process(tls);
    }
    
    class Turtle implements Comparable<Turtle>{
	public int weight;
	public int capacity;
	public Turtle(int w, int c){
	    weight = w; capacity = c;
	}
	public int compareTo(Turtle o){
	    return capacity - o.capacity;
	}
    }
    
    void process(ArrayList<Turtle> tls){
	Collections.sort(tls);
	int[] outs = new int[tls.size()];
	int[] weights = new int[tls.size()];
	outs[0] = 1;
	weights[0] = tls.get(0).weight;
	int[][] dp = new int[tls.size()][tls.size()];
	for(int i = 0; i < dp.length; i++){
	    for(int j = 0; j < dp[0].length; j++){
		if(i == 0 && j == i){
		    dp[i][j] = tls.get(i).weight;
		}else if (i == 0 && j != i){
		    dp[i][j] = Integer.MAX_VALUE;
		}else{
		    int newWeight = dp[i-1][j];
		    if(j-1 >= 0 && dp[i-1][j-1] < Integer.MAX_VALUE &&
		       (tls.get(i).capacity >= tls.get(i).weight + dp[i-1][j-1])){
			if(dp[i-1][j-1] + tls.get(i).weight < newWeight){
			    newWeight = dp[i-1][j-1] + tls.get(i).weight;
			}
		    }
		    dp[i][j] = newWeight;
		}
	    }
	}
	int max = 0;
	for(int i = 0; i < dp[tls.size()-1].length; i++){
	    //	    System.out.println(dp[tls.size()-1][i]);
	    if(dp[tls.size()-1][i] < Integer.MAX_VALUE && max < i + 1){
		max = i + 1;
	    }
	}
	System.out.println(max);
	// for(int i = 0; i < weights.length; i++){
	//     System.out.println(weights[i]);
	// }
    }
}
