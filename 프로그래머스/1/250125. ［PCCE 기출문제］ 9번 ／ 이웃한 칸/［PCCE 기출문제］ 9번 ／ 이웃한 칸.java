import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] board, int h, int w) {
        // board의 길이
        int n = board.length;
        
        // 칸 개수 저장할 count 변수
        int count = 0;
        
        // 탐색 배열
        int[] dh={0,1,-1,0}, dw={1,0,0,-1};
        
        boolean[][] isVisited = new boolean[n][n];
        
        Queue<int[]> q = new ArrayDeque<>();
        
        isVisited[h][w] = true;
        q.add(new int[]{h,w});
        
        while(!q.isEmpty()){
            int[] current = q.poll();
            
            for(int direction=0; direction<4; direction++){
                int nh = h+dh[direction];
                int nw = w+dw[direction];
                
                if(nh<0 || nw<0 || nh>=n || nw >= n)
                    continue;
                if(isVisited[nh][nw])
                    continue;
                if(!board[h][w].equals(board[nh][nw]))
                    continue;
                count++;
                isVisited[nh][nw] = true;
                q.add(new int[]{nh,nw});
            }
        }
        
        return count;
    }
}