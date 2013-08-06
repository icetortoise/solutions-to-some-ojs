package SurroundedRegions;
import java.util.*;
public class Solution {
    public void solve(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int m = board.length;
	if(m == 0) return;
	int n = board[0].length;
	if(n == 0) return;
	int[][] mask = new int[m][n];
	for(int i = 0; i < m; i++){
	    int j1 = 0, j2 = n - 1;
	    if(board[i][j1] == 'O' && mask[i][j1] == 0){
		connectedGraphUpdate(board, i, j1, mask, m, n);
	    }
	    if(board[i][j2] == 'O' && mask[i][j2] == 0){
		connectedGraphUpdate(board, i, j2, mask, m, n);
	    }
	}
	for(int j = 0; j < n; j++){
	    int i1 = 0, i2 = m - 1;
	    if(board[i1][j] == 'O' && mask[i1][j] == 0){
		connectedGraphUpdate(board, i1, j, mask, m, n);
	    }
	    if(board[i2][j] == 'O' && mask[i2][j] == 0){
		connectedGraphUpdate(board, i2, j, mask, m ,n );
	    }
	}
	for(int i = 0; i < m; i++){
	    for(int j = 0; j < n; j++){
		if(mask[i][j] == 1){
		    board[i][j] = 'O';
		}else{
		    board[i][j] = 'X';
		}
	    }
	}
    }
    public void connectedGraphUpdate(char[][] board, int i, int j, int[][] mask, int m, int n){
	Queue<Integer> q = new ArrayDeque<Integer>();
	q.offer(i);
	q.offer(j);
	mask[i][j] = 1;
	while(q.peek() != null){
	    i = q.poll();
	    j = q.poll();
	    if(i - 1 >= 0 && board[i - 1][j] == 'O' && mask[i-1][j] == 0){
		mask[i-1][j] = 1;
		q.offer(i - 1);
		q.offer(j);
	    }
	    if(i + 1 < m && board[i + 1][j] == 'O' && mask[i+1][j] == 0){
		mask[i+1][j] = 1;
		q.offer(i + 1);
		q.offer(j);
	    }
	    if(j - 1 >= 0 && board[i][j - 1] == 'O' && mask[i][j - 1] == 0){
		mask[i][j-1] = 1;		
		q.offer(i);
		q.offer(j-1);
	    }
	    if(j + 1 < n && board[i][j + 1] == 'O' && mask[i][j + 1] == 0){
		mask[i][j+1] = 1;		
		q.offer(i);
		q.offer(j+1);
	    }
	}
    }
    public void connectedGraphUpdateDFS(char[][] board, int i, int j, int[][] mask, int m, int n){
	mask[i][j] = 1;
	if(i - 1 >= 0 && board[i - 1][j] == 'O' && mask[i-1][j] == 0){
	    connectedGraphUpdate(board, i - 1, j, mask, m, n);
	}
	if(i+1 < m && board[i + 1][j] == 'O' && mask[i+1][j] == 0){
	    connectedGraphUpdate(board, i + 1, j, mask, m, n);
	}
	if(j-1 >= 0 && board[i][j-1] == 'O' && mask[i][j-1] == 0){
	    connectedGraphUpdate(board, i, j-1, mask, m, n);
	}
	if(j+1 < n && board[i][j+1] == 'O' && mask[i][j+1] == 0){
	    connectedGraphUpdate(board, i, j+1, mask, m, n);
	}
    }
}
