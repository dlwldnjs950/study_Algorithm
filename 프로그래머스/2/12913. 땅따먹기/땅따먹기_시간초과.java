import java.util.*;
import java.io.*;

/*
N행 4열
1행부터 땅을 밟으며 한 행씩 내려옴
각 행의 4칸 중 한칸만 밟을 수 있다.
    같은 열은 연속해서 밟을 수 없다.
얻을 수 있는 점수의 최대값 return

각 지점에 대해 DFS를 돌려서 마지막에 도착했을 때, 최대값을 저장
*/

class Solution {
    
    int answer = Integer.MIN_VALUE;
    
    int solution(int[][] land) {
        
        for(int startCol=0; startCol<4; startCol++){
            dfs(0, startCol, 0, land.length, land);
        }
        

        return answer;
    }
    
    void dfs(int row, int col, int tmpScore, int rowSize, int[][] land){
        if(row == rowSize){
            answer = Math.max(answer, tmpScore);
            return;
        }
        for(int nextIdx=0; nextIdx<4; nextIdx++){
            if(nextIdx == col)
                continue;
            dfs(row + 1, nextIdx, tmpScore+ land[row][col], land.length, land);
        }
    }
}
