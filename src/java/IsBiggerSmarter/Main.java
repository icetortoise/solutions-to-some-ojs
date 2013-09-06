package IsBiggerSmarter;

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
	ArrayList<Elephant> els = new ArrayList<Elephant>();
	int i = 0;
	while(true){
	    String s = ReadLn(100);
	    if(s == null || s.length()==0){
		break;
	    }
	    els.add(new Elephant(i++, Integer.valueOf(s.split(" ")[0]),
				 Integer.valueOf(s.split(" ")[1])));
	}
	process(els);
    }
    class Elephant implements Comparable<Elephant>{
	public int idx;
	public int weight;
	public int iq;
	public Elephant(int i, int weight, int iq){
	    this.idx = i; this.weight = weight; this.iq = iq;
	}
	public int compareTo(Elephant other){
	    return this.weight - other.weight;
	}
    }
    void process(ArrayList<Elephant> els){
	Collections.sort(els);
	int[] longestDec = new int[els.size()];
	int[] pre = new int[els.size()];
	for(int i = 0; i < longestDec.length; i++){
	    longestDec[i] = 1;
	    pre[i] = i;
	}
	int maxIdx = 0;
	for(int i = 0; i < longestDec.length; i++){
	    for(int j = 0; j < i; j++){
		if(els.get(j).iq > els.get(i).iq && 
		   els.get(j).weight < els.get(i).weight && 
		   longestDec[j] + 1 > longestDec[i]){
		    longestDec[i] = longestDec[j] + 1;
		    pre[i] = j;
		}
	    }
	    if(longestDec[i] > longestDec[maxIdx]){
		maxIdx = i;
	    }
	}
	System.out.println(longestDec[maxIdx]);
	printPath(pre, maxIdx, els);
    }
    void printPath(int[] pre, int idx, ArrayList<Elephant> els){
	if(pre[idx] < idx){
	    printPath(pre, pre[idx], els);
	    System.out.println(els.get(idx).idx+1);
	}else{
	    System.out.println(els.get(idx).idx+1);
	}
    }
}
