import java.util.*;
/*
길이 같은 두개의 큐
한큐poll=>그원소다른큐offer
각 큐의 합이 같도록 만들자
필요한 작업의 최소 횟수
poll+offer가 작업 1회

큐를 배열로 줌
*/
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2=0;
        Queue<Integer> que1 = new ArrayDeque<>();
        for(int q:queue1){
            que1.offer(q);
            sum1+=q;
        }
        Queue<Integer> que2 = new ArrayDeque<>();
        for(int q:queue2){
            que2.offer(q);
            sum2+=q;
        }
        
        int answer = 0;
        while(sum1 != sum2) {
            if(answer > (queue1.length + queue2.length) * 2)
                return -1;
            int val = 0;
            if(sum1 < sum2) {
                val = que2.poll();
                que1.offer(val);
                sum1 += (long) val;
                sum2 -= (long) val;
            }
            else if(sum1 > sum2) {
                val = que1.poll();
                que2.offer(val);
                sum1 -= (long) val;
                sum2 += (long) val;
            }
            else 
            {
                return answer;
            }
            answer++;
        }
        return answer;
    }
}