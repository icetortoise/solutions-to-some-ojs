package EditStepLadders;

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
	ArrayList<String> dict = new ArrayList<String>();
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	int idx = 0;

	while(true){
	    String word = Main.ReadLn(20);
	    if(word == null) break;
	    dict.add(word);
	    for(int i : editCandidates(idx, dict)){
		adjList.get(i).add(idx);
	    }
	    adjList.add(new ArrayList<Integer>());
	    idx++;
	}
	// for(int i = 0; i < adjList.size(); i++){
	//     ArrayList<Integer> l = adjList.get(i);
	//     System.out.println(dict.get(i) + ":");
	//     for(int j : l){
	// 	System.out.println(dict.get(j));
	//     }
	//     System.out.println();
	// }
	int[] visited = new int[adjList.size()];
	int max = 0;
	for(int i = 0; i<visited.length; i++){
	    if(visited[i] == 0){
		int maxL = dfs(i, adjList, visited);
		if(maxL > max){
		    max = maxL;
		}
	    }
	}
	System.out.println(max);
    }
    
    public static int dfs(int idx, ArrayList<ArrayList<Integer>> adjList, int[] visited){
	if(adjList.get(idx).size() == 0){
	    visited[idx] = 1;
	    return 1;
	}else{
	    int max = 0;
	    for(int can : adjList.get(idx)){
		if(visited[can] == 0){
		    int dis = dfs(can, adjList, visited);
		    if(dis > max){
			max = dis;
		    }
		}else{
		    if(visited[can] > max){
			max = visited[can];
		    }
		}
	    }
	    visited[idx] = max+1;
	    return max+1;
	}
    }

    public static ArrayList<Integer> editCandidates(int idx, ArrayList<String> dict){
	String word = dict.get(idx);
	ArrayList<Integer> result = new ArrayList<Integer>();
	for(int i = 0; i<idx;i++){
	    if(isEdit(dict.get(i),word)){
		result.add(i);
	    }
	}
	return result;
    }

    public static boolean isEdit(String a, String b){
	int al = a.length();	int bl = b.length();
	if(al - bl > 1 || al - bl < -1){
	    return false;
	}
	if(al < bl){
	    int i = 0; int j = 0;
	    for(; i < bl && j < al; i++){
		if(a.charAt(j) != b.charAt(i)){
		    if(i - j >= 1){
			return false;
		    }
		}else{
		    j++;
		}
	    }
	    return (i == j) || i - j == 1;
	}
	if(al > bl){
	    int i = 0; int j = 0;
	    for(; i < al && j < bl; i++){
		if(a.charAt(i) != b.charAt(j)){
		    if(i - j >= 1){
			return false;
		    }
		}else{
		    j++;
		}
	    }
	    return i - j <= 1;
	}
	if(al == bl){
	    int diff = 0;
	    for(int i = 0; i < al; i++){
		if(a.charAt(i) != b.charAt(i)){
		    diff++;
		    if(diff > 1){
			return false;
		    }
		}
	    }
	    return diff == 1;
	}
	return false; // never reach
    }
}
