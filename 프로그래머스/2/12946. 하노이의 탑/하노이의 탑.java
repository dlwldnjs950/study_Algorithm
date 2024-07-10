import java.util.*;

class Solution {
    List<int[]> answer = new ArrayList<>();
    public List<int[]> solution(int n) {
        
        hanoi(n, 1, 2, 3);
        return answer;
    }
    
    void hanoi(int N, int start, int mid, int end){
        if(N == 0)
            return;
        hanoi(N -1, start, end, mid);
        answer.add(new int[]{start, end});
        hanoi(N-1, mid, start, end);
    }
}