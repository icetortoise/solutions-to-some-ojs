package bicoloring;
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
	boolean allDone = false;
	while(!allDone){
	    String l = Main.ReadLn(8);
	    if(l.equals("0")){
		allDone = true;
	    }else{
		String edges = Main.ReadLn(8);
		bicolor(l, edges);
	    }
	}
    }

    public void bicolor(String nodes, String edges){
	int nNode = Integer.valueOf(nodes);
	int nEdges = Integer.valueOf(edges);
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	for(int i = 0 ; i < nNode; i++){
	    ArrayList<Integer> emp = new ArrayList<Integer>();
	    adjList.add(emp);
	}
	for(int i = 0; i < nEdges; i++){
	    String e = Main.ReadLn(8);
	    String[] edge = e.split(" ");
	    adjList.get(Integer.valueOf(edge[0])).add(Integer.valueOf(edge[1]));
	    adjList.get(Integer.valueOf(edge[1])).add(Integer.valueOf(edge[0]));
	}
	boolean[] visited = new boolean[nNode];
	ArrayList<Integer> q = new ArrayList<Integer>();
	q.add(0);
	while(q.size() != 0){
	    HashSet<Integer> internal = new HashSet<Integer>(q);
	    ArrayList<Integer> newq = new ArrayList<Integer>();
	    for(int n : q){
		visited[n] = true;
		for(int end : adjList.get(n)){
		    if(internal.contains(end)){
			System.out.println("NOT BICOLORABLE.");
			return;
		    }
		    if(!visited[end]){
			newq.add(end);
		    }
		}
	    }
	    q = newq;
	}
	System.out.println("BICOLORABLE.");
    }
    
    // You can insert more classes here if you want.
}
