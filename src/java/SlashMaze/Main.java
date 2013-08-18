package SlashMaze;

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
	int count = 1;
	while(true){
	    String[] colRow = Main.ReadLn(16).split(" ");
	    int ncol = Integer.valueOf(colRow[0]);
	    int nrow = Integer.valueOf(colRow[1]);
	    if(ncol == 0 && nrow == 0){
		return;
	    }else{
		System.out.println("Maze #"+Integer.toString(count++)+":");
		helper(ncol, nrow);
		System.out.println();
	    }
	}
    }
    public void helper(int ncol, int nrow){
	boolean[][] visited = new boolean[2*nrow-1][ncol];
	char[][] maze = new char[nrow][ncol];
	ArrayList<Integer> result = new ArrayList<Integer>();
	for(int i = 0; i < nrow; i++){
	    maze[i] = Main.ReadLn(16).toCharArray();
	}
	for(int i = 0; i < 2*nrow-1; i++){
	    for(int j = 0; j < ncol; j++){
		if(!visited[i][j]){
		    dfs(i, j, visited, maze, 1, result);
		}
	    }
	}
	int max = 0;
	for(int x : result){
	    if(x > max)
		max = x;
	}
	if(result.size() == 0){
	    System.out.println("There are no cycles.");
	}else{
	    System.out.println(Integer.toString(result.size()) + 
			   " Cycles; the longest has length " + Integer.toString(max)+ ".");
	}
    }
    public boolean valid(int[] path, boolean[][] visited){
	return path[0] >= 0 && path[0] <visited.length && path[1]>=0 && path[1] < visited[0].length;
    }
    public void dfs(int i, int j, boolean[][] visited, char[][] maze, int len, ArrayList<Integer> result){
	visited[i][j] = true;
	int[] pathA = new int[2];
	int[] pathB = new int[2];
	boolean pathASet = false;
	boolean pathBSet = false;
	if(i % 2 == 0){
	    // check (i-1, j-1) (i+1,j-1) (i-1, j) (i+1,j)
	    if(j-1>=0){
		if(maze[i/2][j-1] == '/'){
		    if(!pathASet){
			pathA[0] = i+1;pathA[1] = j-1;
			pathASet = true;;
		    }else{
			pathB[0] = i+1;pathB[1] = j-1;
			pathBSet = true;
		    }
		}else if(maze[i/2][j-1] == '\\'){ 
		    if(!pathASet){
			pathA[0] = i-1;pathA[1] = j-1;
			pathASet = true;;
		    }else{
			pathB[0] = i-1;pathB[1] = j-1;
			pathBSet = true;
		    }
		}
	    }
	    if(maze[i/2][j] == '/'){
		if(!pathASet){
		    pathA[0] = i-1;pathA[1] = j;
		    pathASet = true;
		}else{
		    pathB[0] = i-1;pathB[1] = j;
		    pathBSet = true;
		}
	    }else if(maze[i/2][j] == '\\'){
		if(!pathASet){
		    pathA[0] = i+1;pathA[1] = j;
		    pathASet = true;;
		}else{
		    pathB[0] = i+1;pathB[1] = j;
		    pathBSet = true;
		}
	    }
	}else{
	    //check (i-1, j) (i+1,j) (i-1, j+1) (i+1,j+1)
	    if(maze[i/2][j] == '/'){
		if(!pathASet){
		    pathA[0] = i-1;pathA[1] = j+1;
		    pathASet = true;;
		}else{
		    pathB[0] = i-1;pathB[1] = j+1;
		    pathBSet = true;
		}
	    }else if(maze[i/2][j] == '\\'){
		if(!pathASet){
		    pathA[0] = i-1;pathA[1] = j;
		    pathASet = true;;
		}else{
		    pathB[0] = i-1;pathB[1] = j;
		    pathBSet = true;
		}
	    }
	    if(maze[i/2+1][j] == '/'){
		if(!pathASet){
		    pathA[0] = i+1;pathA[1] = j;
		    pathASet = true;;
		}else{
		    pathB[0] = i+1;pathB[1] = j;
		    pathBSet = true;
		}
	    }else if(maze[i/2+1][j] == '\\'){
		if(!pathASet){
		    pathA[0] = i+1;pathA[1] = j+1;
		    pathASet = true;;
		}else{
		    pathB[0] = i+1;pathB[1] = j+1;
		    pathBSet = true;
		}
	    }
	}
	if(!pathBSet){
	    if(valid(pathA,visited) && !visited[pathA[0]][pathA[1]]){
		dfs(pathA[0],pathA[1],visited, maze,len+1,result);
	    }
	}else if (!valid(pathA,visited) || !valid(pathB,visited)){
	    if(valid(pathA,visited) && !visited[pathA[0]][pathA[1]]){
		dfs(pathA[0],pathA[1],visited, maze,len+1,result);
	    }else if (valid(pathB,visited) && !visited[pathB[0]][pathB[1]]){
		dfs(pathB[0],pathB[1],visited, maze,len+1,result);
	    }
	}else{
	    if(visited[pathA[0]][pathA[1]] && visited[pathB[0]][pathB[1]]){
		result.add(len);
	    }else if(!visited[pathA[0]][pathA[1]]){
		dfs(pathA[0],pathA[1],visited, maze,len+1,result);
	    }else{
		dfs(pathB[0],pathB[1],visited, maze,len+1,result);
	    }
	}
    }
}
