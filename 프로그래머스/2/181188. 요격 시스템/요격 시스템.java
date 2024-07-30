import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        
        Arrays.sort(targets, (a,b)->a[1] - b[1]);
        
        // 지금 구간 안에 속할 수 있는지 확인...
        int answer = 0;
        int currentEnd = 0;
        for(int[] target : targets){
            int start = target[0];
            int end = target[1];
            
            if(start >= currentEnd){
                currentEnd = end;
                answer++;
            }
        }
        
        return answer;
    }
}