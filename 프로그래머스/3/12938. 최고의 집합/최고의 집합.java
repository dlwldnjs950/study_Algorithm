import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        PriorityQueue<Integer> arr = new PriorityQueue<>();
        
        int share = s / n;
        int reminder = s % n;
        
        // 자연수로 만들 수 없음
        if(share == 0)
            return new int[]{-1};
        
        // 나머지 만큼은 share +1
        int[] answer = new int[n];
        for(int idx = 0; idx < n; idx++){
            answer[idx] = share;
            if(idx >= n-reminder)
                answer[idx]++;
        }
        
        return answer;
    }
}