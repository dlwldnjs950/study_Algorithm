import java.util.*;

class Solution {
    
    int[] qSpot;
    int answer;
    public int solution(int n) {
        
        // 경우의 수
        answer = 0;    
        // 해당 행의 몇 열에 퀸이 있는지 표시
        qSpot = new int[n];
        
        nQueen(n, 0);
        
        return answer;
    }
    
    void nQueen(int size, int cnt){
        
        // 현재까지 놓아둔게 조건에 만족하는지
        if(!isOk(cnt - 1))
            return;
        
        // 마지막까지 다 놓았으면 정답체크
        if(cnt == size){
            answer++;
            return;
        }
        for(int idx = 0; idx < size; idx++){
            qSpot[cnt] = idx;
            nQueen(size, cnt + 1);
        }        
    }
    
    boolean isOk(int cnt){
        for(int idx = 0; idx<cnt; idx++){
            // 같은 열에 놓이면 안됨
            if(qSpot[idx] == qSpot[cnt])
                return false;
            
            // 대각선 방향에 놓이면 안됨
            // 대각선 방향끼리는 행값차 = 열값차
            if(cnt - idx == Math.abs(qSpot[cnt] - qSpot[idx]))
                return false;
            
        }
        return true;
    }
}