import java.util.*;
import java.io.*;

/*
N행 4열
1행부터 땅을 밟으며 한 행씩 내려옴
각 행의 4칸 중 한칸만 밟을 수 있다.
    같은 열은 연속해서 밟을 수 없다.
얻을 수 있는 점수의 최대값 return

각 지점에 대해 DFS를 돌려서 마지막에 도착했을 때, 최대값을 저장

모든 경우의 수를 탐색하면 시간 초과가 난다.
DP를 활용한다...........DP 넘 어려워잉
*/

class Solution {
    
    
    
    int solution(int[][] land) {
        
        int answer = 0;
        
        int[][] dp = new int[land.length + 1][4];
        
        // 각 행별 합
        for(int rowIdx = 1; rowIdx<=land.length; rowIdx++){
            
            // 각 열별 합
            for(int colIdx = 0; colIdx<4; colIdx++){
                // 해당 열에서 가장 큰 값 더하기
                int max = 0;
                for(int findIdx = 0; findIdx<4; findIdx++){
                    if(colIdx == findIdx)
                        continue;
                    // 이전 행에 대한 새로운 최대값 찾기 라서 dp의 열 값을 for문으로 바꿔가며 확인
                    dp[rowIdx][colIdx] = Math.max(dp[rowIdx][colIdx], dp[rowIdx-1][findIdx] + land[rowIdx-1][colIdx]);
                }
                answer = Math.max(answer, dp[rowIdx][colIdx]);
            }
        }
        
        
        return answer;
    }
}