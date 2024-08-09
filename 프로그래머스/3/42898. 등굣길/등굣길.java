import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] count = new int[m + 1][n + 1];
        
        for(int[] pud : puddles){
            count[pud[0]][pud[1]] = -1;
        }
        
        // 집을 1로 초기화
        // 왼쪽/위쪽 둘 중에 최대값
        count[1][1] = 1;
        for(int rIdx = 1; rIdx<= m; rIdx++){
            for(int cIdx = 1; cIdx<= n; cIdx++){
                
                // 웅덩이는 지나갈 방법이 없음
                if(count[rIdx][cIdx] == -1){
                    count[rIdx][cIdx] = 0;
                    continue;
                }
                // 아래 방향 이동
                if(rIdx > 1){
                    count[rIdx][cIdx] += count[rIdx -1][cIdx];
                }
                // 오른쪽 방향 이동
                if(cIdx > 1){
                    count[rIdx][cIdx] += count[rIdx][cIdx -1];
                }        
                
                count[rIdx][cIdx] %= 1000000007;
            }
        }
        return count[m][n];
    }
}