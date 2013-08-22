package TowerOfCubes;

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
	int c = 1;
	boolean consec = false;
	while(true){
	    int cubes = Integer.valueOf(Main.ReadLn(100));
	    if(cubes == 0){
		return;
	    }else{
		if(consec)
		    System.out.println();
		System.out.println("Case #" + Integer.toString(c++));
		int[][] cubeArray = new int[cubes][6];
		for(int i = 0; i<cubes;i++){
		    String[] line = Main.ReadLn(100).split(" ");
		    for(int j = 0; j < 6; j++){
			cubeArray[i][j] = Integer.valueOf(line[j]);
		    }
		}
		helper(cubeArray);
		consec = true;
	    }
	}
    }
    public void helper(int[][] cubeArray){
	ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	HashMap<Integer,ArrayList<Integer>> colorToNodes = new HashMap<Integer,ArrayList<Integer>>();
	int[] lens = new int[cubeArray.length * 6];
	int[] path = new int[cubeArray.length * 6];
	for(int i = 0; i<cubeArray.length; i++){
	    for(int j = 0; j < cubeArray[i].length; j++){
		ArrayList<Integer> adjL = new ArrayList<Integer>();
		if(colorToNodes.containsKey(cubeArray[i][j])){
		    adjL.addAll(colorToNodes.get(cubeArray[i][j]));
		}
		adjList.add(adjL);
		if(adjL.size()==0){
		    lens[i*6+j] = 1;
		    path[i*6+j] = -1;
		}else{
		    int len = 0;
		    int next = -1;
		    for(int adj : adjL){
			if(len < lens[adj]+1){
			    len = lens[adj]+1;
			    next = adj;
			}
		    }
		    lens[i*6+j] = len;
		    path[i*6+j] = next;
		}
	    }
	    for(int j = 0; j < cubeArray[i].length; j++){
		int oppo = j%2==0?j+1:j-1;
		if(colorToNodes.containsKey(cubeArray[i][oppo])){
		    colorToNodes.get(cubeArray[i][oppo]).add(i*6+j);
		}else{
		    ArrayList<Integer> ns = new ArrayList<Integer>();
		    ns.add(i*6+j);
		    colorToNodes.put(cubeArray[i][oppo], ns);
		}
	    }
	}
	int idx = -1; int max = 0;
	for(int i = 0; i < lens.length; i++){
	    if(max <= lens[i]){
		max = lens[i];
		idx = i;
	    }
	}
	System.out.println(max);
	int[] result = new int[max];
	for(int i = 0; i < result.length; i++){
	    result[i] = idx;
	    idx = path[idx];
	}
	for(int i = result.length-1; i >= 0; i--){
	    System.out.println(Integer.toString(result[i]/6 + 1) + " " + toSide(result[i]%6));
	}
    }
    
    public String toSide(int i){
	if(i == 0){
	    return "front";
	}else if(i == 1){
	    return "back";
	}else if(i == 2){
	    return "left";
	}else if(i == 3){
	    return "right";
	}else if(i == 4){
	    return "top";
	}else if(i == 5){
	    return "bottom";
	}else{
	    return "impossible!";
	}
    }
}
