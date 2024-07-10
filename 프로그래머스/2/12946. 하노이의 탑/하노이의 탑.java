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
        // start에 있는 가장 큰 판을 옮기기 위해
        // start에 있는 N-1개를 end를 임시기둥으로 사용해 mid 기둥에 옮겨두기
        hanoi(N -1, start, end, mid);
        answer.add(new int[]{start, end});
        // 가장 큰 판을 옮겼으면
        // mid기둥에 옮겨놨던 N-1개를 end로 옮기기
        hanoi(N-1, mid, start, end);
    }
}
