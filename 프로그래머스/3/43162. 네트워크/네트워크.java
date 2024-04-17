import java.util.*;
import java.io.*;

/* 
컴퓨터의 개수와 연결 정보가 주어질 때, 네트워크 개수를 리턴
*/

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] isVisited = new boolean[n];
        for(int computer=0; computer<n; computer++){
            if(isVisited[computer])
                continue;
            dfs(computer, n, computers, isVisited);
            answer++;
        }
        
        return answer;
    }
    
    void dfs(int computer, int n, int[][] computers, boolean[] isVisited){
        isVisited[computer] = true;
        for(int idx = 0; idx<n; idx++){
            if(isVisited[idx])
                continue;
            if(computers[computer][idx] == 0)
                continue;
            dfs(idx, n, computers, isVisited);
        }
    }
}