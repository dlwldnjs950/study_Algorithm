import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(int w : works){
            queue.add(w);
        }
        
        for(int loop=0; loop < n; loop++){
            int max = queue.poll();
            if(max == 0)
                return 0;
            queue.add(max - 1);
        }
        
        while(!queue.isEmpty()){
            int tmp = queue.poll();
           // System.out.println(tmp);
            answer += (long) Math.pow(tmp,2);
        }
        
        
        return answer;
    }
}