import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Queue<Integer> wait = new ArrayDeque<>();
        Queue<Integer> prior = new PriorityQueue<>(Comparator.reverseOrder());
        
        for(int idx=0; idx<priorities.length; idx++){
            wait.offer(idx);
            prior.offer(priorities[idx]);
        }
        
        while(!wait.isEmpty()){
            int candidate = wait.poll();
            //System.out.println(candidate);
            int candiPrior = priorities[candidate];
            
            int nowProcessPrior = prior.peek();
            
            // 우선 순위가 낮으면 다시 대기열로
            if(candiPrior < nowProcessPrior){
                wait.offer(candidate);
            }else if(candiPrior == nowProcessPrior){
                prior.poll();
                answer++;
                if(candidate == location)
                    return answer;
            }
        }
        
        return answer;
    }
}