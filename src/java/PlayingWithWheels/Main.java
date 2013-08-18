package PlayingWithWheels;

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
	String c = Main.ReadLn(16);
	int cnt = Integer.valueOf(c);
	while(cnt-- > 0){
	    Main.ReadLn(16);
	    impl();
	}
    }
    public void impl(){
	String source = Main.ReadLn(16);
	String target = Main.ReadLn(16);
	int fcount = Integer.valueOf(Main.ReadLn(16));
	HashSet<String> forbid = new HashSet<String>();
	while(fcount-- > 0){
	    String x = Main.ReadLn(16);
	    forbid.add(x);
	}
	int step = 0;
	ArrayList<String> q = new ArrayList<String>();
	HashSet<String> visited = new HashSet<String>();
	q.add(source);
	while(q.size() != 0){
	    ArrayList<String> newq = new ArrayList<String>();
	    for(String s : q){
		if(visited.contains(s)){
		    continue;
		}else{
		    visited.add(s);
		    if(s.equals(target)){
			System.out.println(step);
			return;
		    }else{
			newq.addAll(getCandidates(s, visited, forbid));
		    }
		}
	    }
	    q = newq;
	    step++;
	}
	System.out.println(-1);	
    }
    
    public ArrayList<String> getCandidates(String s, HashSet<String> visited, HashSet<String> forbid){
	ArrayList<String> result = new ArrayList<String>();
	for(int i = 0; i < 4; i++){
	    char[] charA = s.toCharArray();
	    if(charA[2*i] == '9'){
		charA[2*i] = '0';
	    }else{
		charA[2*i] += 1;
	    }
	    String can = new String(charA);
	    if(!visited.contains(can) && !forbid.contains(can)){
		result.add(can);
	    }
	}
	for(int i = 0; i < 4; i++){
	    char[] charA = s.toCharArray();
	    if(charA[2*i] == '0'){
		charA[2*i] = '9';
	    }else{
		charA[2*i] -= 1;
	    }
	    String can = new String(charA);
	    if(!visited.contains(can) && !forbid.contains(can)){
		result.add(can);
	    }
	}
	return result;
    }
}
