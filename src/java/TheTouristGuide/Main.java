package TheTouristGuide;

/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;

public class Main implements Runnable{
    public static String ReadLn(int maxLength){  // utility function to read from stdin,
                                          // Provided by Programming-challenges, edit for style only
        byte line[] = new byte [maxLength];
        int length = 0;
        int input = -1;
        try{
            while (length < maxLength){//Read untill maxlength
                input = System.in.read();
                if ((input < 0) || (input == '\n')) break; //or untill end of line ninput
                line [length++] += input;
            }

            if ((input < 0) && (length == 0)) return null;  // eof
            return new String(line, 0, length);
        }catch (IOException e){
            return null;
        }
    }

    public static void main(String args[])  // entry point from OS
    {
        Main myWork = new Main();  // Construct the bootloader
        myWork.run();            // execute
    }

    public void run() {
        new myStuff().run();
    }
}
class myStuff implements Runnable{
    public void run(){
	// Your program here
	int scenario = 1;
	while(true){
	    String[] nae = Main.ReadLn(16).split(" ");
	    if(Integer.valueOf(nae[0]) == 0 && Integer.valueOf(nae[1]) == 0){
		break;
	    }else{
		System.out.println("Scenario #" + Integer.toString(scenario));
		helper(Integer.valueOf(nae[0]), Integer.valueOf(nae[1]));
		scenario++;
	    }
	}
    }
    public void helper(int nnode, int nedge){
	int[] minCapacity = new int[nnode];
	ArrayList<ArrayList<DestAndCapacity>> adjList = new ArrayList<ArrayList<DestAndCapacity>>();
	for(int i = 0; i < nnode; i++){
	    minCapacity[i] = 0;
	    adjList.add(new ArrayList<DestAndCapacity>());
	}
	for(int i = 0; i < nedge; i++){
	    String[] stw = Main.ReadLn(16).split(" ");
	    int source = Integer.valueOf(stw[0])-1;
	    int target = Integer.valueOf(stw[1])-1;
	    int capacity = Integer.valueOf(stw[2]);
	    adjList.get(source).add(new DestAndCapacity(target, capacity));
	    adjList.get(target).add(new DestAndCapacity(source, capacity));
	}
	String[] sdc = Main.ReadLn(16).split(" ");
	int start = Integer.valueOf(sdc[0])-1;
	int end = Integer.valueOf(sdc[1])-1;
	int nppl = Integer.valueOf(sdc[2]);
	minCapacity[start] = Integer.MAX_VALUE;
	bfs(start, adjList, minCapacity);
	int cap = minCapacity[end];
	int answer = (int)Math.ceil((double)nppl/(double)(cap-1));
	System.out.println("Minimum Number of Trips = " + Integer.toString(answer));
	System.out.println();
    }

    public void bfs(int node, ArrayList<ArrayList<DestAndCapacity>> adjList, int[] minCapacity){
	ArrayList<Integer> q = new ArrayList<Integer>();
	q.add(node);
	while(q.size()!=0){
	    ArrayList<Integer> newq = new ArrayList<Integer>();
	    for(int n : q){
		ArrayList<DestAndCapacity> targets= adjList.get(n);
		for(DestAndCapacity dac : targets){
		    if(java.lang.Math.min(minCapacity[n], dac.capacity) > minCapacity[dac.destination]){
			minCapacity[dac.destination] = java.lang.Math.min(minCapacity[n], dac.capacity);
			newq.add(dac.destination);
		    }
		}
	    }
	    q = newq;
	}
    }

    class DestAndCapacity{
	public int destination;
	public int capacity;	
	public DestAndCapacity(int dest, int cap){
	    destination = dest;
	    capacity = cap;
	}
    }
}
